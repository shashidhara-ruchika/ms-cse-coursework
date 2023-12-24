// Get a reference to the success alert
const successAlert = document.getElementById("successAlert");

// Get references to form elements and HelpBlock divs
const nameInput = document.getElementById("name");
const nameHelpBlock = document.getElementById("nameHelpBlock");
const emailInput = document.getElementById("email");
const emailHelpBlock = document.getElementById("emailHelpBlock");
const passwordInput = document.getElementById("password");
const passwordHelpBlock = document.getElementById("passwordHelpBlock");
const confirmPasswordInput = document.getElementById("confirmPassword");
const confirmPasswordHelpBlock = document.getElementById(
    "confirmPasswordHelpBlock"
);

// Get a reference to the submit button
const submitButton = document.querySelector("button[type='submit']");

// Get a reference to the progress bar element
const progressBar = document.getElementById("progressBar");

// Calculate the total number of fields to validate
const totalFields = 4; // Name, Email, Password, Confirm Password

// Reset the form elements to their default state
function resetForm() {
    nameInput.classList.remove("is-valid", "is-invalid");
    emailInput.classList.remove("is-valid", "is-invalid");
    passwordInput.classList.remove("is-valid", "is-invalid");
    confirmPasswordInput.classList.remove("is-valid", "is-invalid");
}

// Validation function for the name
function validateName() {
    const name = nameInput.value;
    const isValid = /^[A-Za-z]{3,50}$/.test(name);
    if (isValid) {
        nameHelpBlock.style.display = "none";
        nameInput.classList.remove("is-invalid");
        nameInput.classList.add("is-valid");
        updateProgressBar();
        return true;
    } else {
        nameHelpBlock.style.display = "block";
        nameInput.classList.remove("is-valid");
        nameInput.classList.add("is-invalid");
        updateProgressBar();
        return false;
    }
}

// Validation function for the email
function validateEmail() {
    const email = emailInput.value;
    // A simple email format validation
    const isValid = /^.*@northeastern\.edu$/.test(email);
    if (isValid) {
        emailHelpBlock.style.display = "none";
        emailInput.classList.remove("is-invalid");
        emailInput.classList.add("is-valid");
        updateProgressBar();
        return true;
    } else {
        emailHelpBlock.style.display = "block";
        emailInput.classList.remove("is-valid");
        emailInput.classList.add("is-invalid");
        updateProgressBar();
        return false;
    }
}

// Validation function for the password
function validatePassword() {
    const password = passwordInput.value;
    const isValid = /^(?=.*[A-Z])(?=.*[\W_])(?=.{8,20}$).*/.test(password);
    if (isValid) {
        passwordHelpBlock.style.display = "none";
        passwordInput.classList.remove("is-invalid");
        passwordInput.classList.add("is-valid");
        updateProgressBar();
        return true;
    } else {
        passwordHelpBlock.style.display = "block";
        passwordInput.classList.remove("is-valid");
        passwordInput.classList.add("is-invalid");
        updateProgressBar();
        return false;
    }
}

// Validation function for confirming the password
function validateConfirmPassword() {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;
    const isValid = password === confirmPassword;
    if (isValid) {
        confirmPasswordHelpBlock.style.display = "none";
        confirmPasswordInput.classList.remove("is-invalid");
        confirmPasswordInput.classList.add("is-valid");
        updateProgressBar();
        return true;
    } else {
        confirmPasswordHelpBlock.style.display = "block";
        confirmPasswordInput.classList.remove("is-valid");
        confirmPasswordInput.classList.add("is-invalid");
        updateProgressBar();
        return false;
    }
}

// Event listeners for input fields to trigger validation and progress bar update
nameInput.addEventListener("input", function () {
    validateName();
});

emailInput.addEventListener("input", function () {
    validateEmail();
});

passwordInput.addEventListener("input", function () {
    validatePassword();
});

confirmPasswordInput.addEventListener("input", function () {
    validateConfirmPassword();
});

// Validation function for the entire form
function validateForm() {
    const isNameValid = validateName();
    const isEmailValid = validateEmail();
    const isPasswordValid = validatePassword();
    const isConfirmPasswordValid = validateConfirmPassword();

    // Check if all fields are valid
    if (
        isNameValid &&
        isEmailValid &&
        isPasswordValid &&
        isConfirmPasswordValid
    ) {
        // Show the success alert
        successAlert.style.display = "block";

        // Hide the success alert after 3 seconds
        setTimeout(function () {
            successAlert.style.display = "none";
            window.location.href = "home.html";
        }, 3000);

        // Reset the form
        document.querySelector("form").reset();
        resetForm();
    }
}

// Event listener for the form submit
document
    .querySelector("form")
    .addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent the form from submitting
        validateForm();
    });

// Function to update the progress bar
function updateProgressBar() {
    const validFields = document.querySelectorAll(
        ".form-control.is-valid"
    ).length;
    const progress = (validFields / totalFields) * 100;
    progressBar.style.width = progress + "%";

    if(validFields == totalFields){
        submitButton.removeAttribute("disabled");
    }
    else {
        submitButton.setAttribute("disabled", "true");
    }
}