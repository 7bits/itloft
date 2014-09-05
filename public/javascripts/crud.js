$(document).ready(function() {

  var DATE_FORMAT = "yyyy-mm-dd hh:ii";
  var DATE_INPUT_SELECTOR = ".crud-datetime";

  // Initializes datetimepicker
  var dtp = $(DATE_INPUT_SELECTOR).datetimepicker({
      format: DATE_FORMAT,
      autoclose: true,
      todayBtn: true,
      minuteStep: 5,
      language: 'ru'
  });

  // Fills shown date field with date from hidden field
  var $dateDivs = $(DATE_INPUT_SELECTOR).parent();
  $dateDivs.toArray().forEach(function (dateDiv) {
    var unixTime = $(dateDiv).find('input[type="hidden"]').val();
    if (unixTime != "") {
      var date = new Date(unixTime * 1000);
      var format = dtp.datetimepicker.DPGlobal.parseFormat(DATE_FORMAT, 'standard');
      var formattedDate = dtp.datetimepicker.DPGlobal.formatDate(date, format, 'ru', 'standard');
      $(dateDiv).find('input[type="text"]').attr("value", formattedDate);
    }
  });

  // On date change with datetimepicker change hidden date
  dtp.on('changeDate', function(ev){
    $(ev.target).parent().find('input[type="hidden"]').attr("value", ev.date.valueOf() / 1000);
    });
});
