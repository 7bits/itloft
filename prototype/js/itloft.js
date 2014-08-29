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