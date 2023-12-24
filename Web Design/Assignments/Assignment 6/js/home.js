var tooltipTriggerList = [].slice.call(
  document.querySelectorAll('[data-bs-toggle="tooltip"]')
);
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl);
});
// JavaScript for posts validation
document.getElementById("postsButton").addEventListener("click", function () {
  const postsText = document.getElementById("postsText").value;
  const errorText = document.getElementById("errorText");

  if (postsText.trim() === "") {
    errorText.innerText = "Please enter a posts.";
  } else {
    // Clear the error message and optionally add the posts to the timeline.
    errorText.innerText = "";
    const posts = document.createElement("div");
    posts.className = "posts";
    posts.innerHTML = `
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">NEU Sanskriti</h5>
              <p class="card-text">${postsText}</p>
            </div>
          </div>
        `;
    document.getElementById("postsList").appendChild(posts);

    // Clear the posts text input
    document.getElementById("postsText").value = "";
  }
});

document
  .getElementById("uploadImageButton")
  .addEventListener("click", function () {
    const imageInput = document.getElementById("imageInput");
    const postsText = document.getElementById("postsText").value;
    const errorText = document.getElementById("errorText");

    if (postsText.trim() === "" && imageInput.files.length === 0) {
      errorText.innerText = "Please enter a posts or upload an image.";
    } else {
      // Clear the error message
      errorText.innerText = "";

      if (imageInput.files.length > 0) {
        // User has selected an image, process it
        const imageFile = imageInput.files[0];
        const reader = new FileReader();

        reader.onload = function (event) {
          const imageData = event.target.result;
          // Create a new posts element with the image
          const posts = document.createElement("div");
          posts.className = "posts";
          posts.innerHTML = `
                <div class="card">
                  <img class="card-img-top" src="${imageData}" alt="Uploaded Image">
                  <div class="card-body">
                    <h5 class="card-title">User Name</h5>
                    <p class="card-text">${postsText}</p>
                  </div>
                </div>
              `;
          document.getElementById("postsList").appendChild(posts);
        };

        reader.readAsDataURL(imageFile);
      } else {
        // Create a new posts element without an image
        const posts = document.createElement("div");
        posts.className = "posts";
        posts.innerHTML = `
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title">User Name</h5>
                  <p class="card-text">${postsText}</p>
                </div>
              </div>
            `;
        document.getElementById("postsList").appendChild(posts);
      }

      // Clear the posts text input and image input
      document.getElementById("postsText").value = "";
      imageInput.value = "";
    }
  });

  // Add a JavaScript function to close the modal when the "Upload" button is clicked
  document.getElementById('uploadImageButton').addEventListener('click', function() {
    $('#imageUploadModal').modal('hide'); // Close the modal
  });
