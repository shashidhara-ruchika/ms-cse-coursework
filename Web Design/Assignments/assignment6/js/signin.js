// Get a reference to the success alert
const successAlert = document.getElementById("successAlert");

// Get a reference to the "Sign In" button and the spinner
const signInButton = document.getElementById("signInButton");
const spinner = signInButton.querySelector(".spinner-border");

// Get references to form elements and HelpBlock divs
const emailInput = document.getElementById("email");
const emailHelpBlock = document.getElementById("emailHelpBlock");
const passwordInput = document.getElementById("password");
const passwordHelpBlock = document.getElementById("passwordHelpBlock");

// Get a reference to the progress bar element
const progressBar = document.getElementById("progressBar");

// Calculate the total number of fields to validate
const totalFields = 2; // Email, Password

// Reset the form elements to their default state
function resetForm() {
    emailInput.classList.remove("is-valid", "is-invalid");
    passwordInput.classList.remove("is-valid", "is-invalid");
}

// Validation function for the email
function validateEmail() {
    const email = emailInput.value;
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

// Event listeners for input fields to trigger validation and progress bar update

emailInput.addEventListener("input", function () {
    validateEmail();
});

passwordInput.addEventListener("input", function () {
    validatePassword();
});

// Validation function for the entire form
function validateForm() {
    const isEmailValid = validateEmail();
    const isPasswordValid = validatePassword();

    // Check if all fields are valid
    if (
        isEmailValid &&
        isPasswordValid
    ) {
        // Show the success alert
        successAlert.style.display = "block";

        // Hide the success alert after 3 seconds
        setTimeout(function () {
            successAlert.style.display = "none";
            window.location.href = "home.html";
        }, 1000);

        // Reset the form
        document.querySelector("form").reset();
        resetForm();
        

    }
}


// Function to update the progress bar
function updateProgressBar() {
    const validFields = document.querySelectorAll(
        ".form-control.is-valid"
    ).length;
    console.log("validFields", validFields)
    const progress = (validFields / totalFields) * 100;
    progressBar.style.width = progress + "%";
}

// Function to show the spinner
function showSpinner() {
    signInButton.disabled = true;
    spinner.style.display = "inline-block";
}

// Function to hide the spinner
function hideSpinner() {
    signInButton.disabled = false;
    spinner.style.display = "none";
}

// Event listener for the form submit
document.querySelector("form").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent the form from submitting
    showSpinner(); // Show the spinner when the form is submitted

    // Simulate a delay 
    setTimeout(function () {
        validateForm();

        // Hide the spinner after the validation
        hideSpinner();

    }, 500); // Simulate a 2-second delay
});