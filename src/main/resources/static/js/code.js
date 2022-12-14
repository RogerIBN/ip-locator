// Query the map of the user's IP address and display it in the browser
$("#search-location").click(function () {
  // Log the click event
  console.log(
    "Click on the button to search for the location of the IP address"
  );
  // Get the data from the api
  $.get("api/v1/position", function (openStreetMap) {
    // Get the map element
    let ipMap = document.getElementById("mapa-ip");
    // Set the src attribute of the map element
    ipMap.src = openStreetMap.url;
  });
});
