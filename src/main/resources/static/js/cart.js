// Wait for the DOM to be fully loaded
document.addEventListener("DOMContentLoaded", function() {
  // Get all the add-to-cart buttons
  var addToCartButtons = document.getElementsByClassName("add-to-cart");

  // Add event listener to each button
  Array.from(addToCartButtons).forEach(function(button) {
    button.addEventListener("click", function() {
      // Get the product ID from the button's data-product-id attribute
      var productId = button.getAttribute("data-product-id");

      // Send an AJAX request to add the product to the cart
      addToCart(productId);
    });
  });

  // Function to handle adding the product to the cart
  function addToCart(productId) {
    // Perform necessary actions to add the product to the cart
        // This could involve making a server request or updating the session data

        // Make an AJAX request to add the product to the cart
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/add-to-cart?productId=" + productId);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onload = function() {
          if (xhr.status === 200) {
            // Success, redirect to the cart page
            window.location.href = "/cart";
          } else {
            // Handle error response
            console.error("Failed to add product to cart");
          }
        };
        xhr.send();

        // Alternatively, you can use fetch API for the AJAX request
        /*
        fetch("/add-to-cart?productId=" + productId, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          }
        })
          .then(response => {
            if (response.ok) {
              // Success, redirect to the cart page
              window.location.href = "/cart";
            } else {
              // Handle error response
              console.error("Failed to add product to cart");
            }
          })
          .catch(error => {
            console.error("Failed to add product to cart:", error);
          });
        */
    // Redirect to the cart page or show a success message
    window.location.href = "/cart"; // Replace "/cart" with the appropriate URL for the cart page
  }
});
