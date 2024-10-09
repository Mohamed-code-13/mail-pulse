# Mail Pulse - Gmail Clone

**Mail Pulse** is a full-featured Gmail clone built using modern web technologies. It provides users with a familiar email experience where they can send, receive, and manage emails seamlessly. The project is built using **Vue.js** for the frontend, **Spring Boot** for the backend, and **PostgreSQL** for the database.

## Features

### User Authentication

- **Signup**: New users can create accounts.
- **Login**: Users can securely log into their accounts.

### Email Management

- **Send Emails**: Users can compose and send emails to other registered users.
- **Receive Emails**: Users can receive and manage emails in their inbox.
- **Draft Emails**: Users can save email drafts and return later to edit and send them.
- **Send Attachments**: Users can attach files to their emails.

### Folder Customization

- **Custom Folders**: Users can create custom folders to organize their emails.
- **Move Emails**: Users can move emails between folders (including custom folders).

### Contact Management

- **Frequent Contacts**: Users can add frequent contacts to manage and organize their communication.

### Trash and Restore

- **Trash**: Deleted emails are moved to the trash.
- **Restoring**: Users can restore emails from the trash within a set period (e.g., 30 days) before they are permanently deleted.

## Tech Stack

### Frontend

- **Vue.js**: A progressive JavaScript framework for building user interfaces.
- **Vue Router**: For navigation and routing within the app.
- **Vuex**: State management pattern + library for Vue.js applications.

### Backend

- **Spring Boot**: Java-based framework for building the backend REST APIs.

### Database

- **PostgreSQL**: A powerful, open-source object-relational database system.

## Installation

### Prerequisites

- **Node.js** and **npm** (for frontend)
- **Java** and **Maven** (for backend)
- **PostgreSQL** (for database)

### Backend Setup

1. Clone the repository.

   ```bash
   git clone https://github.com/Mohamed-code-13/mail-pulse.git
   cd mail-pulse/back-end-mail-server
   ```

2. Configure PostgreSQL connection in `application.properties` file.

3. Build and run the Spring Boot backend.
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Frontend Setup

1. Navigate to the frontend directory.

   ```bash
   cd mail-pulse/front-end-mail-server
   ```

2. Install dependencies.

   ```bash
   npm install
   ```

3. Run the development server.

   ```bash
   npm run dev
   ```

## Usage

1. **Signup**: Create a new user account.
2. **Login**: Use your credentials to log in.
3. **Email Management**: Start sending, receiving, and organizing emails using folders and contacts.

## License

This project is licensed under the MIT License.
