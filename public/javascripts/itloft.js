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

  // Activates BX Slider
  $('.bxslider').bxSlider({
    auto: true
  });

  // Activates BX Slider for history
  $('.bxslider-history').bxSlider({
    minSlides: 3,
    maxSlides: 3,
    slideWidth: '33.333333%'

  });
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