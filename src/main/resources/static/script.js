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
    { checkbox: 'showRegPassword', fields: ['regPassword', 'regConfirmPassword'] },
    { checkbox: 'showResidentPassword', fields: ['loginResidentPassword'] },
    { checkbox: 'showAdminPassword', fields: ['loginAdminPassword'] }
  ];

  passwordToggles.forEach(item => {
    const checkbox = document.getElementById(item.checkbox);
    checkbox?.addEventListener('change', function () {
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

        residentPanel?.classList.toggle('hidden', target !== 'residentLoginPanel');
        adminPanel?.classList.toggle('hidden', target !== 'adminLoginPanel');
      });
    });
  }

  // ===============================
  // OPTIONAL ACTIVITY LOG SUPPORT
  // ===============================
  // If activity-log.js is loaded BEFORE this script and defines window.recordActivity,
  // then this will work. Otherwise it safely does nothing.
  const recordActivity = window.recordActivity;

  // ===============================
  // RESIDENT REGISTRATION LOGIC
  // ===============================
  const registerForm = document.getElementById('registerForm');

  registerForm?.addEventListener('submit', async e => {
    e.preventDefault();

    const firstname = document.getElementById("regFirstName")?.value.trim();
    const lastname = document.getElementById("regLastName")?.value.trim();
    const phone = document.getElementById("regMobile")?.value.trim();
    const email = document.getElementById("regEmail")?.value.trim();
    const password = document.getElementById("regPassword")?.value.trim();
    const confirmPassword = document.getElementById("regConfirmPassword")?.value.trim();
    const termsChecked = document.getElementById('regTermsCheckbox')?.checked;

    // Validation
    if (!firstname || !lastname || !phone || !password || !confirmPassword) {
      alert("Please fill in all required fields.");
      return;
    }

    if (!termsChecked) {
      alert("Please agree to the Terms & Conditions before registering.");
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

      const response = await fetch("http://localhost:8080/api/resident/register", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: formData
      });

      if (!response.ok) {
        alert("Registration failed. Please check your details.");
        return;
      }

      const resident = await response.json();
      localStorage.setItem("residentId", resident.id);

      // Activity log: registration success
      if (typeof recordActivity === "function") {
        recordActivity(
          "REGISTER",
          "RESIDENT",
          email || phone,
          `${firstname} ${lastname}`,
          { status: "PENDING" }
        );
      }

      document.getElementById('registerModal')?.style.setProperty('display', 'none');
      registerForm.reset();
      window.location.href = "R_Waiting.html";

    } catch (err) {
      alert("Error connecting to server.");
      console.error(err);
    }
  });

  // ===============================
  // RESIDENT LOGIN
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
        // Activity log: failed login
        if (typeof recordActivity === "function") {
          recordActivity(
            "FAILED_LOGIN",
            "RESIDENT",
            phone,
            "Unknown Resident",
            { reason: "Invalid credentials" }
          );
        }

        alert("Invalid login credentials!");
        return;
      }

      const resident = await res.json();
      localStorage.setItem("residentId", resident.id);

      // Activity log: successful login
      if (typeof recordActivity === "function") {
        recordActivity(
          "LOGIN",
          "RESIDENT",
          phone,
          `${resident.firstname || ""} ${resident.lastname || ""}`.trim() || "Resident",
          { status: resident.status }
        );
      }

      if (resident.status === "PENDING") {
        window.location.href = "R_Waiting.html";
      } else if (resident.status === "ACTIVE") {
        window.location.href = "ResidentPage.html";
      } else {
        alert("Your account status is not recognized.");
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
        // Activity log: admin login success
        if (typeof recordActivity === "function") {
          recordActivity(
            "LOGIN",
            "ADMIN",
            identifier,
            "Admin"
          );
        }

        alert("Admin login successful!");
        window.location.href = "AdminPage.html";

      } else {
        // Activity log: failed admin login
        if (typeof recordActivity === "function") {
          recordActivity(
            "FAILED_LOGIN",
            "ADMIN",
            identifier,
            "Unknown Admin",
            { reason: "Invalid credentials" }
          );
        }

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

  modal.addEventListener('mousemove', () => {
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
