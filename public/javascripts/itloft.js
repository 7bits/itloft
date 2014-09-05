$(document).ready(function() {

  if ($(".col-future").width() < $(window).width() / 2) {
    setEqualHeight($(".col-past, .col-future"));
  }

  $(".js-coworking-info").click(function(event) {

    event.preventDefault();
    $(".js-coworking-box").css("display", "block");
  });

  $(".js-coworking-close").click(function(event) {

    event.preventDefault();
    $(".js-coworking-box").css("display", "none");
  });

  $(".js-social-logo").mouseover(function() {

      var src = $(this).attr("src").match(/[^\.]+/) + "-2.png";
      $(this).attr("src", src);
  });

  $(".js-social-logo").mouseout(function() {

      var src = $(this).attr("src").replace("-2.png", ".png");
      $(this).attr("src", src);
  });

  // Initializes datetimepicker
  $(".form-datetime").datetimepicker({
      format: "yyyy-mm-dd hh:ii",
      autoclose: true,
      startDate: new Date(),
      todayBtn: true,
      minuteStep: 5,
      language: 'ru'
  });

  // Fills file path field for input files elements
  $(".js-file-browse").on("change", function() {
    $(this).parent().parent().find(".js-file-path").val(this.value);
  });

  // Activates BX Slider for main page
  $('.bxslider').bxSlider({
    auto: true
  });

  var HISTORY_SLIDES = 2;

  // Events history page
  if ($('.bxslider-history').size() > 0) {
    // Activates BX Slider for events history
    var historySlider = $('.bxslider-history').bxSlider({
      minSlides: HISTORY_SLIDES,
      maxSlides: HISTORY_SLIDES,
      moveSlides: HISTORY_SLIDES,
      infiniteLoop: false,
      slideWidth: '9999px',
      hideControlOnEnd: true,
      pager: false
    });

    // Scroll BX Slider to current month or to last slide
    var $slides = $('.bxslider-history').find("li");
    var scrollTo = ($slides.length > 0)? $slides.length - 1 : 0;
    for (var i=0; i<$slides.length; i++) {
      if ($slides.eq(i).hasClass("history-active")) {
        scrollTo = i;
      }
    }
    var scrollToSlide = Math.floor(scrollTo/HISTORY_SLIDES);
    historySlider.goToSlide(scrollToSlide);
  }
});

$(window).resize(function() {

  if ($(".col-future").width() < $(window).width() / 2) {
    setEqualHeight($(".col-past, .col-future"));
  } else {
    $(".col-future").height("auto");
    $(".col-past").height("auto");
  }
});

function setEqualHeight(columns) {

  var tallestcolumn = 0;
  columns.each(function() {
    currentHeight = $(this).height();
    if(currentHeight > tallestcolumn) {
      tallestcolumn = currentHeight;
    }
  });
  columns.height(tallestcolumn);
}