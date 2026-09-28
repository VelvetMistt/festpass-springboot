# FestPass Frontend Generation Task

You are building the frontend for my existing college project called **FestPass**.

## 1. IMPORTANT PROJECT RULES

- Work ONLY inside the current React/Vite project: `festpass-ui`.
- Do NOT modify, delete, rename, or create files in the Spring Boot backend project.
- Do NOT change any backend Java code.
- Do NOT change the backend API endpoints.
- Do NOT use fake/mock data.
- The frontend must use the actual Spring Boot REST APIs.
- Use React with JavaScript, not TypeScript.
- Use functional React components and React hooks.
- Keep the implementation simple, clean, stable, and suitable for a college project demonstration.
- Do not over-engineer the application.
- Do not add unnecessary libraries.
- Keep the existing Vite project structure unless changes are actually required.
- Make sure the application runs with `npm run dev`.

## 2. BACKEND

The existing Spring Boot backend is already working.

Backend base URL:

http://localhost:8081

The frontend must communicate with this backend.

### Event APIs

GET all events:
GET http://localhost:8081/events

GET one event:
GET http://localhost:8081/events/{id}

Create event:
POST http://localhost:8081/events

Update event:
PUT http://localhost:8081/events/{id}

Delete event:
DELETE http://localhost:8081/events/{id}

### Ticket APIs

GET all tickets:
GET http://localhost:8081/tickets

GET one ticket:
GET http://localhost:8081/tickets/{id}

Create ticket:
POST http://localhost:8081/tickets

Update ticket:
PUT http://localhost:8081/tickets/{id}

Delete ticket:
DELETE http://localhost:8081/tickets/{id}

### Check-in API

POST:

http://localhost:8081/tickets/checkin?qrCode={qrCode}

The QR token is generated automatically by the backend when a ticket is created.

### Attendance API

GET:

http://localhost:8081/tickets/attendance/{eventId}

This returns the tickets that have been successfully checked in for that event.

## 3. APPLICATION PURPOSE

FestPass is an event and ticket management system.

The system allows:

1. Event creation and management
2. Ticket registration
3. Event capacity control
4. Automatic QR token generation for tickets
5. Ticket check-in using QR token
6. Attendance viewing

The frontend should make these features easy to demonstrate to an evaluator.

## 4. UI DESIGN

Create a modern, professional and clean college-project dashboard.

Use a polished visual style with:

- Dark blue / navy primary theme
- White cards
- Subtle shadows
- Rounded corners
- Clean typography
- Good spacing
- Professional buttons
- Clear status badges
- Responsive layout
- Sidebar navigation
- Top header
- Dashboard cards

The UI should look like a real event management application, not like the default Vite template.

Application name:

**FestPass**

Subtitle:

**Event & Ticket Management System**

Use a simple FestPass logo/text in the sidebar.

Do not make the design overly complicated.

## 5. MAIN LAYOUT

Create a dashboard layout with:

### Sidebar

Navigation items:

- Dashboard
- Events
- Tickets
- Check-in
- Attendance

The currently selected page should be visually highlighted.

### Top Header

Show:

**FestPass**

and a small subtitle:

**Event & Ticket Management System**

## 6. DASHBOARD PAGE

Create a professional dashboard.

Show summary cards:

### Total Events

Number of events returned by:

GET /events

### Total Tickets

Number of tickets returned by:

GET /tickets

### Checked In

Number of tickets where:

checkedIn === true

### Available Capacity

Calculate a simple total capacity from the events.

Show a section:

**Upcoming Events**

Display event cards/table with:

- Event name
- Date
- Venue
- Capacity
- Ticket price

Use actual backend data.

Do NOT hard-code event information.

## 7. EVENTS PAGE

Create an Events management page.

Display all events from:

GET /events

Show them in clean cards or a professional table.

Each event should display:

- Event ID
- Event name
- Date
- Venue
- Capacity
- Ticket price

Provide:

### Create Event button

Open a clean modal or form.

Fields:

- Event name
- Date
- Venue
- Capacity
- Ticket price

Send the form to:

POST /events

Example JSON:

{
  "name": "Tech Fest 2026",
  "date": "2026-10-15",
  "venue": "Main Auditorium",
  "capacity": 200,
  "ticketPrice": 150
}

After successful creation:

- Show success message
- Close the form
- Refresh event list

### Edit Event

Provide an Edit button.

Use:

PUT /events/{id}

### Delete Event

Provide a Delete button.

Use:

DELETE /events/{id}

Ask for confirmation before deletion.

Handle 404 and validation errors properly.

## 8. TICKETS PAGE

Create a professional Tickets page.

Display tickets from:

GET /tickets

Show:

- Ticket ID
- Attendee name
- Attendee email
- Event ID
- Ticket number
- QR token
- Check-in status

Use a badge:

Checked In

or

Not Checked In

Do not expose extremely long QR tokens in an ugly way. Truncate them visually if necessary while keeping the actual value available.

### Create Ticket

Provide a Create Ticket form.

Fields:

- Attendee name
- Attendee email
- Event ID
- Ticket number

Do NOT ask the user to enter QR code.

The backend automatically generates the QR token.

Send:

POST /tickets

Example:

{
  "attendeeName": "Ashifa",
  "attendeeEmail": "ashifa@example.com",
  "eventId": 1,
  "ticketNumber": "FP1001"
}

After creation:

- Show success message
- Refresh ticket list
- Display the generated QR token

Handle capacity-full errors clearly.

## 9. CHECK-IN PAGE

Create a dedicated Check-in page.

Make it visually simple and professional.

Show:

**Ticket Check-in**

Input:

QR Token

Button:

**Check In**

When the user enters a valid QR token, call:

POST /tickets/checkin?qrCode={qrCode}

On successful check-in:

Show a clear success message such as:

"Ticket checked in successfully"

Display:

- Attendee name
- Email
- Event ID
- Ticket number
- Check-in status

If the ticket was already checked in:

Show:

"Ticket already checked in"

If the QR token is invalid:

Show:

"Invalid QR code"

Do not fake the result. Use the actual backend response.

## 10. ATTENDANCE PAGE

Create an Attendance page.

Provide an Event ID input or event selector.

When an event is selected, call:

GET /tickets/attendance/{eventId}

Display:

- Attendee name
- Email
- Ticket number
- Event ID
- Check-in status

Show a small summary:

**Checked-in attendees: X**

where X is the number returned by the backend.

If no attendees are checked in, show a clean empty-state message.

## 11. ERROR HANDLING

Handle API errors properly.

Examples:

- Backend unavailable
- Invalid input
- Event not found
- Ticket not found
- Event capacity full
- Invalid QR code
- Ticket already checked in

Display user-friendly messages.

Do not display raw technical errors unnecessarily.

Also show loading states while API requests are running.

## 12. VALIDATION

Frontend validation should include:

### Event

- Event name required
- Date required
- Venue required
- Capacity must be greater than 0
- Ticket price cannot be negative

### Ticket

- Attendee name required
- Valid email required
- Event ID required
- Ticket number required

Do not duplicate complicated backend logic. The backend remains the final validation authority.

## 13. QR TOKEN DISPLAY

The current backend generates a UUID QR token.

Do NOT assume the backend returns a QR image.

Display the generated QR token clearly.

If adding a visual QR representation requires an additional dependency, do not add unnecessary complexity unless it is simple and stable.

The important requirement is that the actual backend-generated QR token must be used for check-in.

## 14. API IMPLEMENTATION

Create a clean API utility/helper if useful.

Use:

fetch()

or another lightweight method already available.

Do not install Axios unless there is a real reason.

Use:

const API_BASE_URL = "http://localhost:8081";

Do not hard-code fake responses.

Every displayed event/ticket/statistic should come from the backend whenever applicable.

## 15. COMPONENT STRUCTURE

Organize the React frontend cleanly.

A possible structure is:

src/
├── components/
├── pages/
├── services/
├── App.jsx
├── App.css
├── index.css
└── main.jsx

You may adjust the structure if needed, but keep it simple.

Suggested components/pages:

- Dashboard
- Events
- Tickets
- CheckIn
- Attendance
- Sidebar
- Header
- EventForm
- TicketForm

## 16. NAVIGATION

Do not add a complicated routing system unless necessary.

A simple React state-based page navigation is acceptable.

If React Router is already installed or easy to add, it may be used.

The important requirement is that clicking:

Dashboard
Events
Tickets
Check-in
Attendance

must display the corresponding page without reloading the entire application.

## 17. RESPONSIVE DESIGN

The application should work reasonably on:

- Laptop
- Desktop
- Smaller screen

The sidebar and tables should remain usable.

## 18. VISUAL QUALITY

Make the UI look polished enough for a college evaluator demonstration.

Use:

- Consistent spacing
- Consistent font sizes
- Professional buttons
- Hover effects
- Cards
- Tables
- Status badges
- Empty states
- Loading states
- Success/error notifications
- Clean forms
- Modal dialogs where useful

Avoid:

- Excessive animations
- Huge complicated charts
- Unnecessary pages
- Login/authentication
- Payment gateway
- Admin authentication
- Unnecessary external services

## 19. IMPORTANT BACKEND SAFETY RULE

The Spring Boot backend already works correctly.

DO NOT modify:

- Java backend files
- Spring Boot controllers
- Services
- Repositories
- Models
- MySQL configuration
- API endpoint names
- API request formats

The frontend must adapt to the existing backend.

## 20. CORS

The React frontend runs on:

http://localhost:5173

The Spring Boot backend runs on:

http://localhost:8081

If browser CORS prevents the frontend from communicating with the backend, clearly report that issue.

Do not silently replace the API with mock data.

If a very small frontend-side configuration is enough, use it.

Otherwise tell me exactly what backend CORS configuration is required rather than changing unrelated backend code.

## 21. FINAL REQUIREMENT

After implementing the frontend:

1. Make sure the project builds.
2. Make sure `npm run dev` works.
3. Make sure the default Vite page is completely replaced by the FestPass UI.
4. Make sure all API calls use `http://localhost:8081`.
5. Make sure no fake/mock event or ticket data is used.
6. Make sure all five sections work:
   - Dashboard
   - Events
   - Tickets
   - Check-in
   - Attendance
7. Keep the code beginner-readable.
8. Do not create unnecessary complexity.
9. Do not modify the Spring Boot backend unless CORS is specifically required.
10. If you encounter an error, fix it instead of leaving broken code.

Build the complete frontend now.