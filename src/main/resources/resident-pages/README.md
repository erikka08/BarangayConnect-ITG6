# Resident Pages - BarangayConnect

This folder contains all the HTML and CSS files for the Resident Portal of BarangayConnect.

## File Structure

```
resident-pages/
├── ResidentPage.html              # Main resident portal page
├── resident-style.css            # Main styling for resident portal
├── Home-Dashboard.html            # Resident dashboard
├── home-dashboard-style.css      # Dashboard styling
├── Announcements.html            # Community announcements
├── announcements-style.css       # Announcements styling
├── Emergency.html                # Emergency contacts
├── emergency-style.css           # Emergency styling
├── Reports.html                  # Community reports
├── reports-style.css             # Reports styling
├── Requests.html                 # My requests tracking
├── requests-style.css            # Requests styling
├── Transparency.html             # Transparency portal
├── transparency-style.css        # Transparency styling
└── README.md                     # This file
```

## Features

### Resident Portal (ResidentPage.html)
- **Header**: Same design as admin portal with BarangayConnect branding
- **Sidebar**: Resident-focused navigation with 6 sections:
  - Home Dashboard
  - Announcements
  - Emergency
  - Reports
  - Requests
  - Transparency
- **Main Content**: Dynamic iframe loading for each section

### Home Dashboard
- Welcome message for residents
- Quick action buttons (Submit Request, View Announcements, Emergency Contacts)
- Personal status cards (My Status, My Requests, Notifications)
- Recent announcements panel
- Personal activity tracking

### Announcements
- Community announcements with filtering
- Search functionality
- Important, Events, and Services categories
- Detailed announcement information

### Emergency
- Emergency numbers (911 services)
- Barangay contacts (Captain, Health Center, Barangay Hall)
- Quick action buttons for emergency procedures
- Direct calling functionality

### Reports
- Community statistics and reports
- Population, service requests, and health metrics
- Interactive report viewing

### Requests
- Personal request tracking
- Status monitoring (Pending, Processing, Completed)
- Request details and actions
- New request submission

### Transparency
- Public documents access
- Financial reports, meeting minutes, project reports
- Document download functionality

## Design Features

- **Consistent Styling**: Matches admin portal design language
- **Responsive Design**: Works on all device sizes
- **No Scrollbars**: Clean, full-viewport design
- **Interactive Elements**: Hover effects and smooth transitions
- **Color Scheme**: Green-based theme for community feel
- **Typography**: Poppins font family for modern look

## Usage

1. Open `ResidentPage.html` in a web browser
2. Navigate between sections using the sidebar
3. Each section loads its own HTML file with dedicated styling
4. All interactions are functional with placeholder alerts

## Browser Compatibility

- Chrome (recommended)
- Firefox
- Safari
- Edge

## Notes

- All scrollbars are hidden for a clean appearance
- Iframe content is optimized for full viewport usage
- JavaScript functionality is included for interactive elements
- CSS uses modern features with fallbacks for older browsers







