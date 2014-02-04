$(document).ready(function() {

	if ($(".col-future").width() < $(window).width() / 2) {
		setEqualHeight($(".col-past, .col-future"));
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