document.addEventListener('DOMContentLoaded', () => {
  console.log("✅ Script loaded without crashing!");

  // ===============================
  // ✅ OPEN MODALS (Safe)
  // ===============================
  const registerBtn = document.getElementById('registerBtn');
  const loginBtn = document.getElementById('loginBtn');

  registerBtn?.addEventListener('click', () => {
    document.getElementById('registerModal')?.style.setProperty('display', 'block');
  });

  loginBtn?.addEventListener('click', () => {
    document.getElementById('loginModal')?.style.setProperty('display', 'block');
  });

  // ===============================
  // ✅ OPEN FORGOT PASSWORD MODAL (Safe)
  // ===============================
  const forgotLink = document.getElementById('forgotPasswordLink');
  forgotLink?.addEventListener('click', (e) => {
    e.preventDefault();
    document.getElementById('forgotPasswordModal')?.style.setProperty('display', 'block');
  });

  // ===============================
  // ✅ CLOSE MODALS (Safe)
  // ===============================
  document.querySelectorAll('.close').forEach(btn => {
    btn?.addEventListener('click', e => {
      const modalId = e.currentTarget.dataset.close;
      if (modalId) document.getElementById(modalId)?.style.setProperty('display', 'none');
    });
  });

  // ===============================
  // ✅ SHOW/HIDE PASSWORD (Safe)
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
  // ✅ LOGIN MODAL TABS (Safe)
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
  // ✅ RESIDENT REGISTRATION
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

    if (!firstname || !lastname || !phone || !password || !confirmPassword) {
      alert("Please fill in all required fields.");
      return;
    }

    if (password !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    try {
      const formData = new URLSearchParams();
      formData.append("firstname", firstname);
      formData.append("lastname", lastname);
      formData.append("phone_num", phone);
      formData.append("email", email);
      formData.append("password", password);

      const response = await fetch("http://localhost:8080/api/resident/register", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: formData
      });

      const message = await response.text();
      alert(message);

      document.getElementById('registerModal')?.style.setProperty('display', 'none');
      registerForm.reset();

    } catch (err) {
      alert("Error connecting to server.");
      console.error(err);
    }
  });

  // ===============================
  // ✅ ADMIN LOGIN
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
        window.location.href = "AdminPage.html";
      } else {
        alert("Invalid admin credentials!");
      }
    } catch (err) {
      alert("Server not reachable. Make sure Spring Boot is running.");
      console.error(err);
    }
  });
function attachDragSafeOverlay(modalId, contentSelector, closeFn) {
  const modal = document.getElementById(modalId);
  if (!modal) return;

  const box = modal.querySelector(contentSelector);
  let startedInsideBox = false;
  let dragging = false;

  // Track if mousedown started inside the content box
  modal.addEventListener('mousedown', (e) => {
    startedInsideBox = box?.contains(e.target) ?? false;
    dragging = false;
  });

  // Track if the user moves the mouse (dragging)
  modal.addEventListener('mousemove', (e) => {
    if (startedInsideBox) dragging = true;
  });

  // Only close if click started outside content AND there was no drag
  modal.addEventListener('click', (e) => {
    if (e.target === modal && !startedInsideBox && !dragging) {
      if (typeof closeFn === 'function') closeFn();
      else modal.style.display = 'none';
    }
    startedInsideBox = false;
    dragging = false;
  });

  // Prevent clicks inside content from bubbling
  if (box) {
    ['click', 'mousedown', 'mouseup', 'mousemove'].forEach(evt => {
      box.addEventListener(evt, (e) => e.stopPropagation());
    });
  }
}
});