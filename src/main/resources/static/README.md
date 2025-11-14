# Admin Pages - BarangayConnect

This folder contains all the HTML and CSS files related to the AdminPage.html functionality.

## File Structure

### Main Admin Interface
- **AdminPage.html** - Main admin dashboard with sidebar navigation
- **admin-style.css** - Styles for the main admin interface

### Admin Page Sections
Each section has its own HTML file and corresponding CSS file:

#### 1. Home Dashboard
- **Home-Dashboard.html** - Dashboard overview with summary cards and activity panels
- **home-dashboard-style.css** - Styles for the dashboard page

#### 2. Reports Management
- **Reports.html** - Report generation and management interface
- **reports-style.css** - Styles for the reports page

#### 3. Document Requests
- **Requests.html** - Document request management system
- **requests-style.css** - Styles for the requests page

#### 4. Transparency Portal
- **Transparency.html** - Financial transparency and public documents
- **transparency-style.css** - Styles for the transparency page

#### 5. Announcements
- **Announcements.html** - Community announcement management
- **announcements-style.css** - Styles for the announcements page

#### 6. User Management
- **A_User-Management.html** - User administration and management
- **A_user-management-style.css** - Styles for the user management page

#### 7. Emergency Contacts
- **Emergency.html** - Emergency contact and procedure management
- **emergency-style.css** - Styles for the emergency page

## How It Works

1. **AdminPage.html** serves as the main container with sidebar navigation
2. Each sidebar section loads its corresponding HTML file using iframes
3. Each HTML file has its own dedicated CSS file for styling
4. Navigation between sections is handled by JavaScript in AdminPage.html

## Usage

To access the admin interface, open **AdminPage.html** in a web browser. The sidebar navigation will allow you to switch between different admin sections.

## Dependencies

- Font Awesome icons (loaded via CDN)
- Google Fonts - Poppins (loaded via CDN)
- All CSS files use CSS custom properties (variables) for consistent theming

## File Organization Benefits

- **Modular Structure**: Each section is completely independent
- **Easy Maintenance**: Edit specific sections without affecting others
- **Clean Separation**: HTML and CSS are logically separated
- **Scalable**: Easy to add new sections or modify existing ones
- **Consistent Styling**: All pages maintain the same design language
