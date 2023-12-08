document.getElementById('searchForm').onsubmit = function(e) {
    e.preventDefault();
    var searchQuery = document.getElementsByName('search')[0].value;
    window.location.href = '/search?query=' + encodeURIComponent(searchQuery);
};

document.getElementById('reservationForm').onsubmit = function(e) {
    e.preventDefault();
    var formData = new FormData(document.getElementById('reservationForm'));
    fetch('/submit-request', {
        method: 'POST',
        body: formData
    }).then(function(response) {
        return response.text();
    }).then(function(text) {
        document.getElementById('requestResponse').innerHTML = text;
    });
};

document.getElementById('signupForm').onsubmit = function(e) {
    e.preventDefault();
    var formData = new FormData(document.getElementById('signupForm'));
    fetch('/signup', {
        method: 'POST',
        body: formData
    }).then(function(response) {
        return response.text();
    }).then(function(text) {
        document.getElementById('signupResponse').innerHTML = text; // Update this with your response area
    });
};

document.getElementById('loginForm').onsubmit = function(e) {
    e.preventDefault();

    // Create a FormData object from the form
    var formData = new FormData(document.getElementById('loginForm'));

    // Send a POST request to the /login endpoint
    fetch('/login', {
        method: 'POST',
        body: formData
    }).then(function(response) {
        // If the login is successful, redirect to the homepage or another page
        if (response.ok) {
            window.location.href = '/';
        } else {
            // If the login fails, display an error message
            return response.text();
        }
    }).then(function(text) {
        // Display any returned error message
        document.getElementById('loginResponse').innerHTML = text; // Ensure you have a div with this ID for displaying the message
    });
};
