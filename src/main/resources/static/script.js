// Wait for ALL HTML to load first
document.addEventListener('DOMContentLoaded', () => {
  console.log("✅ Script loaded without crashing!");

  // ===============================
  // OPEN MODALS
  // ===============================
  const registerBtn = document.getElementById('registerBtn');
  const loginBtn = document.getElementById('loginBtn');

  // Open register modal
  registerBtn?.addEventListener('click', () => {
    document.getElementById('registerModal')?.style.setProperty('display', 'block');
  });

  // Open login modal
  loginBtn?.addEventListener('click', () => {
    document.getElementById('loginModal')?.style.setProperty('display', 'block');
  });

  // ===============================
  // FORGOT PASSWORD
  // ===============================
  const forgotLink = document.getElementById('forgotPasswordLink');
  forgotLink?.addEventListener('click', (e) => {
    e.preventDefault();
    document.getElementById('forgotPasswordModal')?.style.setProperty('display', 'block');
  });

  // ===============================
  // CLOSE MODALS (X buttons)
  // ===============================
  document.querySelectorAll('.close').forEach(btn => {
    btn?.addEventListener('click', e => {
      const modalId = e.currentTarget.dataset.close;
      if (modalId) document.getElementById(modalId)?.style.setProperty('display', 'none');
    });
  });

  // ===============================
  // SHOW/HIDE PASSWORD BUTTONS
  // ===============================
  const passwordToggles = [
    { checkbox: 'showRegPassword', fields: ['regPassword','regConfirmPassword'] },
    { checkbox: 'showResidentPassword', fields: ['loginResidentPassword'] },
    { checkbox: 'showAdminPassword', fields: ['loginAdminPassword'] }
  ];

  passwordToggles.forEach(item => {
    const checkbox = document.getElementById(item.checkbox);
    checkbox?.addEventListener('change', function() {
      item.fields.forEach(id => {
        const field = document.getElementById(id);
        if (field) field.type = this.checked ? 'text' : 'password';
      });
    });
  });

  // ===============================
  // LOGIN MODAL TABS (Resident/Admin)
  // ===============================
  const tabButtons = document.querySelectorAll('.tab-button');
  const residentPanel = document.getElementById('residentLoginPanel');
  const adminPanel = document.getElementById('adminLoginPanel');

  if (tabButtons?.length > 0) {
    tabButtons.forEach(btn => {
      btn.addEventListener('click', () => {
        tabButtons.forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        const target = btn.dataset.target;

        // Show only the selected panel
        residentPanel?.classList.toggle('hidden', target !== 'residentLoginPanel');
        adminPanel?.classList.toggle('hidden', target !== 'adminLoginPanel');
      });
    });
  }

  // ===============================
  // RESIDENT REGISTRATION LOGIC
  // ===============================
  const registerForm = document.getElementById('registerForm');

  registerForm?.addEventListener('submit', async e => {
    e.preventDefault();

    // Read user input
    const firstname = document.getElementById("regFirstName")?.value.trim();
    const lastname = document.getElementById("regLastName")?.value.trim();
    const phone = document.getElementById("regMobile")?.value.trim();
    const email = document.getElementById("regEmail")?.value.trim();
    const password = document.getElementById("regPassword")?.value.trim();
    const confirmPassword = document.getElementById("regConfirmPassword")?.value.trim();

    // Validation
    if (!firstname || !lastname || !phone || !password || !confirmPassword) {
      alert("Please fill in all required fields.");
      return;
    }

    if (password !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    // Send registration to backend
    try {
      const formData = new URLSearchParams();
      formData.append("firstname", firstname);
      formData.append("lastname", lastname);
      formData.append("phoneNum", phone);
      formData.append("email", email);
      formData.append("password", password);

      // 🔥 FIXED PART — GET THE RESPONSE
      const response = await fetch("http://localhost:8080/api/resident/register", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: formData
      });

      // 🔥 FIXED — convert to JSON
      const resident = await response.json();

      // 🔥 FIXED — SAVE ID so waiting room can check status
      localStorage.setItem("residentId", resident.id);

      // Close modal + reset form
      document.getElementById('registerModal').style.display = 'none';
      registerForm.reset();

      // Redirect to waiting room
      window.location.href = "R_Waiting.html";

    } catch (err) {
      alert("Error connecting to server.");
      console.error(err);
    }
  });

  // ===============================
  // RESIDENT LOGIN (REAL WORKING ONE)
  // ===============================
  const residentLoginForm = document.getElementById('residentLoginForm');

  residentLoginForm?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const phone = document.getElementById('loginResidentPhone')?.value.trim();
    const password = document.getElementById('loginResidentPassword')?.value.trim();

    if (!phone || !password) {
      alert("Please enter your phone number and password.");
      return;
    }

    try {
      const formData = new URLSearchParams();
      formData.append("identifier", phone);
      formData.append("password", password);

      const res = await fetch("http://localhost:8080/api/resident/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: formData
      });

      if (!res.ok) {
        alert("Invalid login credentials!");
        return;
      }

      const resident = await res.json();
      localStorage.setItem("residentId", resident.id);

      // Redirect Based on Status
      if (resident.status === "PENDING") {
        window.location.href = "R_Waiting.html";
      } else if (resident.status === "ACTIVE") {
        window.location.href = "R_Home-Dashboard.html";
      }

    } catch (err) {
      alert("Could not connect to server.");
      console.error(err);
    }
  });

  // ===============================
  // ADMIN LOGIN
  // ===============================
  const adminLoginForm = document.getElementById('adminLoginForm');

  adminLoginForm?.addEventListener('submit', async e => {
    e.preventDefault();

    const identifier = document.getElementById('loginAdminEmail')?.value.trim();
    const password = document.getElementById('loginAdminPassword')?.value.trim();

    if (!identifier || !password) {
      alert("Please enter your admin username/email and password.");
      return;
    }

    try {
      const formData = new URLSearchParams();
      formData.append("identifier", identifier);
      formData.append("password", password);

      const response = await fetch("http://localhost:8080/api/admin/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: formData
      });

      if (response.ok) {
        alert("Admin login successful!");
        window.location.href = "AdminPage.html"; // Your admin page
      } else {
        alert("Invalid admin credentials!");
      }

    } catch (err) {
      alert("Server not reachable. Make sure Spring Boot is running.");
      console.error(err);
    }
  });

}); // END DOMContentLoaded




// ===============================
// DRAG-SAFE MODAL OVERLAY SYSTEM
// ===============================
function attachDragSafeOverlay(modalId, contentSelector, closeFn) {
  const modal = document.getElementById(modalId);
  if (!modal) return;

  const box = modal.querySelector(contentSelector);
  let startedInsideBox = false;
  let dragging = false;

  modal.addEventListener('mousedown', (e) => {
    startedInsideBox = box?.contains(e.target) ?? false;
    dragging = false;
  });

  modal.addEventListener('mousemove', (e) => {
    if (startedInsideBox) dragging = true;
  });

  modal.addEventListener('click', (e) => {
    if (e.target === modal && !startedInsideBox && !dragging) {
      if (typeof closeFn === 'function') closeFn();
      else modal.style.display = 'none';
    }
    startedInsideBox = false;
    dragging = false;
  });

  if (box) {
    ['click', 'mousedown', 'mouseup', 'mousemove'].forEach(evt => {
      box.addEventListener(evt, (e) => e.stopPropagation());
    });
  }
}
