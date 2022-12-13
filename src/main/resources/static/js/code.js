$("#search-location").click(function () {
  console.log("click");
  $.get("api/v1/position", function (data) {
    document.getElementById("mapa-ip").src = data.url;
  });
});
