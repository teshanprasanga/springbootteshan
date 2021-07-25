$(document).ready(function() {


  // activate when form submit button pressed
  $('form').submit( function(evt) {
    
    // prevent leaving the page
    evt.preventDefault();

    // search for the term in the form
    var $searchField = $('#search');
    var $submitButton = $('#submit');
    
    //disable search button whilst search is happening
    $searchField.prop("disabled", true);
    $submitButton.attr("disabled", true).val("searching ...");
    
    // the AJAX part
    var flickerAPI = "https://api.flickr.com/services/feeds/photos_public.gne?jsoncallback=?";
    var animal = $searchField.val();
    var flickrOptions = {
      tags: animal,
      format: "json"
    };
    function displayPhotos(data) {
      var photoHTML = '<ul>';
      $.each(data.items,function(i,photo) {
        photoHTML += '<li class="grid-25 tablet-grid-50">';
        photoHTML += '<a href="' + photo.link + '" class="image">';
        photoHTML += '<img src="' + photo.media.m + '"></a></li>';
      }); // end each
      photoHTML += '</ul>';
      $('#photos').html(photoHTML);
      
      //re-enable the search and submit buttons
      $searchField.prop("disabled", false);
      $submitButton.attr("disabled", false).val("Search");
    }
    
    $.getJSON(flickerAPI, flickrOptions, displayPhotos);

  }); // end click

}); // end ready