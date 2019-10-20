$(function () {
    $(".menu h2").click(function () {
        $(this).next().slideToggle(200);
        $(this).toggleClass("on");
    });
});
