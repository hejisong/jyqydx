$(window).on("resize", function () {
  var html = document.getElementsByTagName('html')[0];
  /*取到屏幕的宽度*/
  var width = window.innerWidth;
  if (width > 1080) {
    width = 1080;
  }
  else if (width < 320) {
    width = 320;
  }
  var fontSize = 100 / 750 * width;
  /*设置fontsize*/
  html.style.fontSize = fontSize + 'px';
}).trigger("resize");

