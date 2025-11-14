// Open modals
document.getElementById('registerBtn').onclick = () => {
  document.getElementById('registerModal').style.display = 'block';
};
document.getElementById('loginBtn').onclick = () => {
  document.getElementById('loginModal').style.display = 'block';
};

// Open forgot password modal (resident tab)
const forgotLink = document.getElementById('forgotPasswordLink');
if (forgotLink) {
  forgotLink.onclick = (e) => {
    e.preventDefault();
    document.getElementById('forgotPasswordModal').style.display = 'block';
  };
}

// Close modals
document.querySelectorAll('.close').forEach(btn => {
  btn.addEventListener('click', e => {
    const modalId = e.target.dataset.close;
    document.getElementById(modalId).style.display = 'none';
  });
});

// Close when clicking outside modal
window.onclick = e => {
  if (e.target.classList.contains('modal')) {
    e.target.style.display = 'none';
  }
};

// Show/Hide Password (Register)
document.getElementById('showRegPassword').addEventListener('change', function() {
  const passField = document.getElementById('regPassword');
  const confirmPassField = document.getElementById('regConfirmPassword');
  const type = this.checked ? 'text' : 'password';
  passField.type = type;
  confirmPassField.type = type;
});

// Tabs within Login Modal
const tabButtons = document.querySelectorAll('.tab-button');
const residentPanel = document.getElementById('residentLoginPanel');
const adminPanel = document.getElementById('adminLoginPanel');

tabButtons.forEach(btn => {
  btn.addEventListener('click', () => {
    tabButtons.forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    const target = btn.dataset.target;
    if (target === 'residentLoginPanel') {
      residentPanel.classList.remove('hidden');
      adminPanel.classList.add('hidden');
    } else {
      adminPanel.classList.remove('hidden');
      residentPanel.classList.add('hidden');
    }
  });
});

// Show/Hide Password (Resident Login)
const showResidentPassword = document.getElementById('showResidentPassword');
if (showResidentPassword) {
  showResidentPassword.addEventListener('change', function() {
    const loginPass = document.getElementById('loginResidentPassword');
    loginPass.type = this.checked ? 'text' : 'password';
  });
}

// Show/Hide Password (Admin Login)
const showAdminPassword = document.getElementById('showAdminPassword');
if (showAdminPassword) {
  showAdminPassword.addEventListener('change', function() {
    const loginPass = document.getElementById('loginAdminPassword');
    loginPass.type = this.checked ? 'text' : 'password';
  });
}

// Retrieve stored users
function getUsers() {
  return JSON.parse(localStorage.getItem('users')) || [];
}

// Save new user
function saveUser(user) {
  const users = getUsers();
  users.push(user);
  localStorage.setItem('users', JSON.stringify(users));
}

// Admin storage helpers
function getAdmins() {
  return JSON.parse(localStorage.getItem('admins')) || [];
}

function saveAdmin(admin) {
  const admins = getAdmins();
  admins.push(admin);
  localStorage.setItem('admins', JSON.stringify(admins));
}

// Seed default admin if none exist
(function seedDefaultAdmin() {
  const admins = getAdmins();
  if (admins.length === 0) {
    saveAdmin({ username: 'admin', email: 'admin@barangayconnect.local', password: 'admin123' });
  }
})();

// Handle Register
document.getElementById('registerForm').addEventListener('submit', e => {
  e.preventDefault();
  const name = document.getElementById('regName').value.trim();
  const mobile = document.getElementById('regMobile').value.trim();
  const email = document.getElementById('regEmail').value.trim();
  const password = document.getElementById('regPassword').value.trim();
  const confirmPassword = document.getElementById('regConfirmPassword').value.trim();

  if (!name || !mobile || !password || !confirmPassword) {
    alert("Please fill in all required fields (Full Name, Mobile Number, Password, and Confirm Password).");
    return;
  }

  if (password !== confirmPassword) {
    alert("Passwords do not match!");
    return;
  }

  const users = getUsers();
  
  // Check if email is provided and already exists
  if (email) {
    const existingUser = users.find(user => user.email === email);
    if (existingUser) {
      alert("This email is already registered.");
      return;
    }
  }

  // Check if mobile number already exists
  const existingMobile = users.find(user => user.mobile === mobile);
  if (existingMobile) {
    alert("This mobile number is already registered.");
    return;
  }

  saveUser({ name, mobile, email, password });
  alert(`Registration Successful!\nWelcome, ${name}`);
  e.target.reset();
  document.getElementById('registerModal').style.display = 'none';
});

// Handle Resident Login
const residentLoginForm = document.getElementById('residentLoginForm');
if (residentLoginForm) {
  residentLoginForm.addEventListener('submit', e => {
    e.preventDefault();
    const loginInput = document.getElementById('loginResidentEmail').value.trim();
    const password = document.getElementById('loginResidentPassword').value.trim();

    if (!loginInput || !password) {
      alert("Please enter your email/mobile number and password.");
      return;
    }

    const users = getUsers();
    const validUser = users.find(user =>
      (user.email === loginInput || user.mobile === loginInput) && user.password === password
    );

    if (validUser) {
      alert(`Login Successful!\nWelcome back, ${validUser.name}`);
      window.location.href = "home.html";
    } else {
      alert("Invalid email/mobile number or password. Please register first or try again.");
    }
  });
}

// Handle Admin Login
const adminLoginForm = document.getElementById('adminLoginForm');
if (adminLoginForm) {
  adminLoginForm.addEventListener('submit', e => {
    e.preventDefault();
    const identifier = document.getElementById('loginAdminEmail').value.trim();
    const password = document.getElementById('loginAdminPassword').value.trim();

    if (!identifier || !password) {
      alert("Please enter your admin username/email and password.");
      return;
    }

    const admins = getAdmins();
    const admin = admins.find(a => (a.email === identifier || a.username === identifier) && a.password === password);

    if (admin) {
      alert("Admin login successful.");
      window.location.href = "admin.html";
    } else {
      alert("Invalid admin credentials. Hint (default): admin / admin123");
    }
  });
}