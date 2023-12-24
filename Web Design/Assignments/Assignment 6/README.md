# Uni-Verse: Building Campus Connections

Uni-Verse is your gateway to a more connected university experience. Share, discover, and shape campus life, one story at a time. Stay connected and informed with our university community app! Discover and share events, interests, and daily life moments with fellow students, creating a vibrant campus experience.

## Purpose of the Website

Uni-Verse is designed to enhance the university experience by providing a platform for students to connect, share, and discover various aspects of campus life. Whether it's staying informed about events, sharing personal experiences, or engaging with trending topics, Uni-Verse aims to foster a vibrant and connected campus community.

## Features

1. **Pages**: 
    - **Landing page**: index.html
    - **Sign Up page**: signup.html
    - **Sign In page**: signin.html
    - **Home page**: home.html

2. Website is responsive on Laptop, Tablet & Phone screens

3. Form Validations for all fields have been added

4. Used 16+ Bootstrap components

## Bootstrap Components:

1. **Accordion**: An accordion is used in `index.html` to display collapsible content sections.

2. **Alerts**: Alerts are used in various files (`index.html`, `home.html`, `signup.html`, and `signin.html`) to show informative messages or error notifications.

3. **Badge**: Badges are used in `home.html` to showcase priority in the upcoming events section.

4. **Buttons**: Buttons are used throughout the project for various interactions and actions.

5. **Card**: Cards are used in `index.html`, `home.html`, and other files to display content in a structured manner.

6. **Carousel**: A carousel is used in `index.html` for creating a slideshow of images.

7. **Close Button**: Close buttons are used in `index.html` and other files for closing components like alerts and modals.

8. **Collapse**: The collapse component is used in `index.html` and other files to create collapsible content sections.

9. **Dropdowns**: Dropdowns are used in the navigation bar in `index.html` and `home.html` for displaying menu options.

10. **List Group**: List groups are used in `home.html` for displaying lists of items.

11. **Modal**: Modals are used in `index.html`, `home.html`, and other files to display pop-up dialogs.

12. **Navbar**: Navigation bars are used in `index.html`, `home.html`, and other files for site navigation.

13. **Offcanvas**: Offcanvas components are used in `index.html` and other files to create off-canvas sidebars.

14. **Placeholders**: Placeholders are used for simulating content in the `signup.html` and `signin.html` forms.

15. **Progress**: Progress bars are used in `signin.html` to show the progress of form completion.

16. **Spinners**: Spinners are used in `signin.html` to provide visual feedback during form submission.

17. **Tooltips**: Tooltips are used in `index.html` and other files to display additional information when hovering over elements.

## Form Validations:

1. **Submit Button Behavior**:
   - The submit button is initially disabled.
   - It becomes enabled only when all form fields are successfully validated.

2. **Username Validation**:
   - Usernames are expected to consist of alphabetic characters.
   - The username must be at least 3 characters and no more than 50 characters in length.

3. **Email Validation**:
   - User email addresses should end with `@northeastern.edu`.
   - The email must be no more than 50 characters in length.

4. **Password Validation**:
   - Passwords should contain alphanumeric characters.
   - A valid password must include at least:
     - 1 uppercase letter,
     - 1 digit,
     - 1 special character.
   - The password should be at least 8 characters in length and not more than 20 characters in length.

5. **Comfirm Password Validation**:
    - Confirm Password should match Password

These validations are in place to ensure that user input is accurate and secure. The submit button remains disabled until all criteria are met, and error messages are provided to guide users when there are issues with their input dynamically.


