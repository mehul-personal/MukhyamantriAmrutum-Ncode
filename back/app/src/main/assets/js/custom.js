// cufon font replacement class/id
Cufon.replace('h1')('h2')('h3')('h4')('h5')('h6')('.componentheading')('.contentheading')('.feature-title')('.blockheader .t', { hover: true })('.colorblockheader .t', { hover: true })('legend')('.tour_now_title', { hover: true })('.join_now_title', { hover: true })('.spotlight-action')('.edition-main')('.price-tag')('.p-number-1')('.p-number-2')('.p-number-3')('.blocknumber-t')('.slogan-head')('.dropcap-round')('.dropcap-square')('.showcase-title')('.show-title');

// jquery cycle paramters
$(document).ready(function () {
    $('.slideshow').cycle({
        fx: 'fade',  // can be fade, scrollUp, shuffle
        delay: 5000,
        cleartype: !$.support.opacity,
        cleartypeNoBg: true,
        speed: 1000,
        timeout: 10000
    });
    $('#twitter_update_list').cycle({
        fx: 'scrollUp', // can be fade, scrollUp, shuffle
        delay: 5000,
        cleartype: !$.support.opacity,
        cleartypeNoBg: true,
        speed: 1000,
        timeout: 10000
    });
});



function changeTest() {

    var Index = $('#SearchType').val()

    if (Index === "1") {

        window.open('search.html', '_self', false)

    };

    if (Index === "2") {

        window.open('search-hosptial.html', '_self', false)

    };

    if (Index === "3") {

        window.open('search-DialysisCenters.html', '_self', false)

    };

    if (Index === "4") {

        window.open('http://www.magujarat.com/mayojna/SearchBPLFamily.aspx', '_self', false)

    };

    if (Index === "5") {

        window.open('http://www.magujarat.com/MAYOJNA/TVADetailsReprot.aspx', '_self', false)

    };
    if (Index === "6") {

        window.open('http://www.magujarat.com/MAYOJNA/Districtkiosk/SearchBPLFamilyReport.aspx', '_self', false)

    };
   
};

// jquery slideshow paramters
$(function () {
    $(".slidetabs").tabs(".images > div", {
        effect: 'fade',
        fadeOutSpeed: "slow",
        rotate: true

    }).slideshow({
        autopause: true,
        autoplay: true,
        clickable: false,
        interval: 12500
    });
});

//page transition

$(document).ready(function () {
    $(".content-layout").css("display", "none");

    $(".content-layout").fadeIn(500);

    $("#hmenu .li a").click(function (event) {
        event.preventDefault();
        linkLocation = this.href;
        $(".content-layout").fadeOut(500, redirectPage);
    });

    function redirectPage() {
        window.location = linkLocation;
    }
});


//contact form validation
$(function () {
    $('.error').hide(); $(".cool-button").click(function () {
        $('.error').hide(); $("label#invalid_error").hide(); $("label#email_error").hide();
        var name = $("input#contact_name").val();
        var email = $("input#contact_email").val();
        if (email == "") {
            $("label#email_error").show();
            $("input#contact_email").focus();
            return false;
        }
        var emailRegEx = /^([a-zA-Z0-9])(([a-zA-Z0-9])*([\._-])?([a-zA-Z0-9]))*@(([a-zA-Z0-9\-])+(\.))+([a-zA-Z]{2,4})+$/
        var subject = $("input#contact_subject").val();
        var message = $("textarea#contact_message").val();

        if (message == "") {
            $("label#message_error").show();
            $("textarea#contact_message").focus();
            return false;
        }
        if (document.getElementById('contact_email').value.search(emailRegEx) == -1) {
            $("label#invalid_error").show();
            $("input#contact_email").focus();
            return false;
        }
        else {
            //var dataString = 'name=' + name + '&email=' + email + '&subject=' + subject + '&message=' + message; $.ajax({ type: "POST", url: "php/contact.php", data: dataString, success: function () { $('#contact-form').html("<div id='message'></div>"); $('#message').html("<h3>Request Submitted</h3>").append("<p>Thank you for submiting your request. If you have any further questions, you can always mail us @ info@example.com or call our support team on +1 800 000 000</p>").hide().fadeIn(1500, function () { $('#message').append("<img id='checkmark' src='images/submit.png' />"); }); } });
            $("#contact_button1").click();
        }
        return false;
    });
});

//newsletter validation
$(function () {
    $('.error').hide(); $(".cool-button2").click(function () {
        $('.error').hide();
        var news_mail = $("input#news_mail").val(); if (news_mail == "Enter your email address") { $("label#mail_error").show(); $("input#news_mail").focus(); return false; }
        var emailRegEx = /^([a-zA-Z0-9])(([a-zA-Z0-9])*([\._-])?([a-zA-Z0-9]))*@(([a-zA-Z0-9\-])+(\.))+([a-zA-Z]{2,4})+$/
        if (document.getElementById('news_mail').value.search(emailRegEx) == -1) {
            document.getElementById('news_mail').value = 'Invalid email address!'
        }
        else {
            var dataString = 'news_mail=' + news_mail; $.ajax({ type: "POST", url: "php/newsletter.php", data: dataString, success: function () { $('#newsletter').html("<div id='news_message'></div>"); $('#news_message').html("<h3>Subscription done</h3>").append("<p>We'll keep you updated on new versions, updates and latest news. </p>").hide().fadeIn(1500, function () { $('#news_message').append("<img id='mailsent' src='images/mail_sent.png' />"); }); } });
        }
        return false;
    });
});

//twitter js
function twitterCallback2(twitters) {
    var statusHTML = [];
    for (var i = 0; i < twitters.length; i++) {
        var username = twitters[i].user.screen_name;
        var status = twitters[i].text.replace(/((https?|s?ftp|ssh)\:\/\/[^"\s\<\>]*[^.,;'">\:\s\<\>\)\]\!])/g, function (url) {
            return '<a href="' + url + '">' + url + '</a>';
        }).replace(/\B@([_a-z0-9]+)/ig, function (reply) {
            return reply.charAt(0) + '<a href="http://twitter.com/' + reply.substring(1) + '">' + reply.substring(1) + '</a>';
        });
        statusHTML.push('<li><span>' + status + '</span> <a style="font-size:85%" href="http://twitter.com/' + username + '/statuses/' + twitters[i].id_str + '">' + relative_time(twitters[i].created_at) + '</a></li>');
    }
    document.getElementById('twitter_update_list').innerHTML = statusHTML.join('');
}

function relative_time(time_value) {
    var values = time_value.split(" ");
    time_value = values[1] + " " + values[2] + ", " + values[5] + " " + values[3];
    var parsed_date = Date.parse(time_value);
    var relative_to = (arguments.length > 1) ? arguments[1] : new Date();
    var delta = parseInt((relative_to.getTime() - parsed_date) / 1000);
    delta = delta + (relative_to.getTimezoneOffset() * 60);

    if (delta < 60) {
        return 'less than a minute ago';
    } else if (delta < 120) {
        return 'about a minute ago';
    } else if (delta < (60 * 60)) {
        return (parseInt(delta / 60)).toString() + ' minutes ago';
    } else if (delta < (120 * 60)) {
        return 'about an hour ago';
    } else if (delta < (24 * 60 * 60)) {
        return 'about ' + (parseInt(delta / 3600)).toString() + ' hours ago';
    } else if (delta < (48 * 60 * 60)) {
        return '1 day ago';
    } else {
        return (parseInt(delta / 86400)).toString() + ' days ago';
    }
}

//pop-up overlay parameters
$(document).ready(function () {
    var triggers = $(".popup").overlay({
        mask: '#000',
        top: '10%',
        effect: 'apple',
        onBeforeLoad: function () {
            var wrap = this.getOverlay().find(".contentWrap");
            wrap.load(this.getTrigger().attr("href"));
        }
    });
});

//pop-up overlay parameters
$(function () {
    $("a[rel]").overlay({
        mask: '#000',
        effect: 'apple',
        onBeforeLoad: function () {
            var wrap = this.getOverlay().find(".contentWrap");
            wrap.load(this.getTrigger().attr("href"));
        }
    });

    $("#triggers img[rel]").overlay({ effect: 'apple' });
});

$(document).ready(function () {
    var triggers = $(".spotlight-action").overlay({
        mask: '#000',
        top: '10%',
        effect: 'apple',
        onBeforeLoad: function () {
            var wrap = this.getOverlay().find(".contentWrap");
            wrap.load(this.getTrigger().attr("href"));
        }
    });
});

$(document).ready(function () {
    var triggers = $(".member_login").overlay({
        // some mask tweaks suitable for modal dialogs
        mask: {
            color: '#000',
            loadSpeed: 200,
            opacity: 0.9
        },
        top: '25%',
        closeOnClick: false,
        onLoad: function () {
            $("input#username.inputbox").focus();
        }
    });

    $("#login form").submit(function (e) {
        // close the overlay
        triggers.eq(1).overlay().close();
        // get user input
        var input = $("input", this).val();
        // do something with the answer
        triggers.eq(1).html(input);
        // do not submit the form
        return e.preventDefault();
    });

});


/** jquery.easing.js ****************/
/*
* jQuery Easing v1.3 - http://gsgd.co.uk/sandbox/jquery/easing/
*
* Uses the built in easing capabilities added In jQuery 1.1
* to offer multiple easing options
*
* TERMS OF USE - jQuery Easing
* 
* Open source under the BSD License. 
* 
* Copyright ?© 2008 George McGinley Smith
* All rights reserved.
*/
eval(function (p, a, c, k, e, d) { e = function (c) { return (c < a ? '' : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36)) }; if (!''.replace(/^/, String)) { while (c--) { d[e(c)] = k[c] || e(c) } k = [function (e) { return d[e] }]; e = function () { return '\\w+' }; c = 1 }; while (c--) { if (k[c]) { p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]) } } return p }('h.j[\'J\']=h.j[\'C\'];h.H(h.j,{D:\'y\',C:9(x,t,b,c,d){6 h.j[h.j.D](x,t,b,c,d)},U:9(x,t,b,c,d){6 c*(t/=d)*t+b},y:9(x,t,b,c,d){6-c*(t/=d)*(t-2)+b},17:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t+b;6-c/2*((--t)*(t-2)-1)+b},12:9(x,t,b,c,d){6 c*(t/=d)*t*t+b},W:9(x,t,b,c,d){6 c*((t=t/d-1)*t*t+1)+b},X:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t*t+b;6 c/2*((t-=2)*t*t+2)+b},18:9(x,t,b,c,d){6 c*(t/=d)*t*t*t+b},15:9(x,t,b,c,d){6-c*((t=t/d-1)*t*t*t-1)+b},1b:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t*t*t+b;6-c/2*((t-=2)*t*t*t-2)+b},Q:9(x,t,b,c,d){6 c*(t/=d)*t*t*t*t+b},I:9(x,t,b,c,d){6 c*((t=t/d-1)*t*t*t*t+1)+b},13:9(x,t,b,c,d){e((t/=d/2)<1)6 c/2*t*t*t*t*t+b;6 c/2*((t-=2)*t*t*t*t+2)+b},N:9(x,t,b,c,d){6-c*8.B(t/d*(8.g/2))+c+b},M:9(x,t,b,c,d){6 c*8.n(t/d*(8.g/2))+b},L:9(x,t,b,c,d){6-c/2*(8.B(8.g*t/d)-1)+b},O:9(x,t,b,c,d){6(t==0)?b:c*8.i(2,10*(t/d-1))+b},P:9(x,t,b,c,d){6(t==d)?b+c:c*(-8.i(2,-10*t/d)+1)+b},S:9(x,t,b,c,d){e(t==0)6 b;e(t==d)6 b+c;e((t/=d/2)<1)6 c/2*8.i(2,10*(t-1))+b;6 c/2*(-8.i(2,-10*--t)+2)+b},R:9(x,t,b,c,d){6-c*(8.o(1-(t/=d)*t)-1)+b},K:9(x,t,b,c,d){6 c*8.o(1-(t=t/d-1)*t)+b},T:9(x,t,b,c,d){e((t/=d/2)<1)6-c/2*(8.o(1-t*t)-1)+b;6 c/2*(8.o(1-(t-=2)*t)+1)+b},F:9(x,t,b,c,d){f s=1.l;f p=0;f a=c;e(t==0)6 b;e((t/=d)==1)6 b+c;e(!p)p=d*.3;e(a<8.u(c)){a=c;f s=p/4}m f s=p/(2*8.g)*8.r(c/a);6-(a*8.i(2,10*(t-=1))*8.n((t*d-s)*(2*8.g)/p))+b},E:9(x,t,b,c,d){f s=1.l;f p=0;f a=c;e(t==0)6 b;e((t/=d)==1)6 b+c;e(!p)p=d*.3;e(a<8.u(c)){a=c;f s=p/4}m f s=p/(2*8.g)*8.r(c/a);6 a*8.i(2,-10*t)*8.n((t*d-s)*(2*8.g)/p)+c+b},G:9(x,t,b,c,d){f s=1.l;f p=0;f a=c;e(t==0)6 b;e((t/=d/2)==2)6 b+c;e(!p)p=d*(.3*1.5);e(a<8.u(c)){a=c;f s=p/4}m f s=p/(2*8.g)*8.r(c/a);e(t<1)6-.5*(a*8.i(2,10*(t-=1))*8.n((t*d-s)*(2*8.g)/p))+b;6 a*8.i(2,-10*(t-=1))*8.n((t*d-s)*(2*8.g)/p)*.5+c+b},1a:9(x,t,b,c,d,s){e(s==v)s=1.l;6 c*(t/=d)*t*((s+1)*t-s)+b},19:9(x,t,b,c,d,s){e(s==v)s=1.l;6 c*((t=t/d-1)*t*((s+1)*t+s)+1)+b},14:9(x,t,b,c,d,s){e(s==v)s=1.l;e((t/=d/2)<1)6 c/2*(t*t*(((s*=(1.z))+1)*t-s))+b;6 c/2*((t-=2)*t*(((s*=(1.z))+1)*t+s)+2)+b},A:9(x,t,b,c,d){6 c-h.j.w(x,d-t,0,c,d)+b},w:9(x,t,b,c,d){e((t/=d)<(1/2.k)){6 c*(7.q*t*t)+b}m e(t<(2/2.k)){6 c*(7.q*(t-=(1.5/2.k))*t+.k)+b}m e(t<(2.5/2.k)){6 c*(7.q*(t-=(2.V/2.k))*t+.Y)+b}m{6 c*(7.q*(t-=(2.16/2.k))*t+.11)+b}},Z:9(x,t,b,c,d){e(t<d/2)6 h.j.A(x,t*2,0,c,d)*.5+b;6 h.j.w(x,t*2-d,0,c,d)*.5+c*.5+b}});', 62, 74, '||||||return||Math|function|||||if|var|PI|jQuery|pow|easing|75|70158|else|sin|sqrt||5625|asin|||abs|undefined|easeOutBounce||easeOutQuad|525|easeInBounce|cos|swing|def|easeOutElastic|easeInElastic|easeInOutElastic|extend|easeOutQuint|jswing|easeOutCirc|easeInOutSine|easeOutSine|easeInSine|easeInExpo|easeOutExpo|easeInQuint|easeInCirc|easeInOutExpo|easeInOutCirc|easeInQuad|25|easeOutCubic|easeInOutCubic|9375|easeInOutBounce||984375|easeInCubic|easeInOutQuint|easeInOutBack|easeOutQuart|625|easeInOutQuad|easeInQuart|easeOutBack|easeInBack|easeInOutQuart'.split('|'), 0, {}));
/*
* jQuery Easing Compatibility v1 - http://gsgd.co.uk/sandbox/jquery.easing.php
*
* Adds compatibility for applications that use the pre 1.2 easing names
*
* Copyright (c) 2007 George Smith
* Licensed under the MIT License:
*   http://www.opensource.org/licenses/mit-license.php
*/
eval(function (p, a, c, k, e, d) { e = function (c) { return (c < a ? '' : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36)) }; if (!''.replace(/^/, String)) { while (c--) { d[e(c)] = k[c] || e(c) } k = [function (e) { return d[e] }]; e = function () { return '\\w+' }; c = 1 }; while (c--) { if (k[c]) { p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]) } } return p }('0.j(0.1,{i:3(x,t,b,c,d){2 0.1.h(x,t,b,c,d)},k:3(x,t,b,c,d){2 0.1.l(x,t,b,c,d)},g:3(x,t,b,c,d){2 0.1.m(x,t,b,c,d)},o:3(x,t,b,c,d){2 0.1.e(x,t,b,c,d)},6:3(x,t,b,c,d){2 0.1.5(x,t,b,c,d)},4:3(x,t,b,c,d){2 0.1.a(x,t,b,c,d)},9:3(x,t,b,c,d){2 0.1.8(x,t,b,c,d)},f:3(x,t,b,c,d){2 0.1.7(x,t,b,c,d)},n:3(x,t,b,c,d){2 0.1.r(x,t,b,c,d)},z:3(x,t,b,c,d){2 0.1.p(x,t,b,c,d)},B:3(x,t,b,c,d){2 0.1.D(x,t,b,c,d)},C:3(x,t,b,c,d){2 0.1.A(x,t,b,c,d)},w:3(x,t,b,c,d){2 0.1.y(x,t,b,c,d)},q:3(x,t,b,c,d){2 0.1.s(x,t,b,c,d)},u:3(x,t,b,c,d){2 0.1.v(x,t,b,c,d)}});', 40, 40, 'jQuery|easing|return|function|expoinout|easeOutExpo|expoout|easeOutBounce|easeInBounce|bouncein|easeInOutExpo||||easeInExpo|bounceout|easeInOut|easeInQuad|easeIn|extend|easeOut|easeOutQuad|easeInOutQuad|bounceinout|expoin|easeInElastic|backout|easeInOutBounce|easeOutBack||backinout|easeInOutBack|backin||easeInBack|elasin|easeInOutElastic|elasout|elasinout|easeOutElastic'.split('|'), 0, {}));

/** js menu ****************/

jQuery(function () {

    var $ = jQuery;
    // retarder
    $.fn.retarder = function (delay, method) {
        var node = this;
        if (node.length) {
            if (node[0]._timer_) clearTimeout(node[0]._timer_);
            node[0]._timer_ = setTimeout(function () { method(node); }, delay);
        }
        return this;
    };

    // base rules
    $('#hornav').addClass('js-active');
    $('#hornav ul div', '#hornav').css('visibility', 'hidden');
    if (!$('#hornav li.current').length) $('#hornav li:first').addClass('active');
    $('.menu>li', '#hornav').hover(
        function () {
            var ul = $('div:first', this);
            if (ul.length) {
                if (!ul[0].hei) ul[0].hei = ul.height();
                ul.css({ height: 20, overflow: 'hidden' }).retarder(10, function (i) {
                    i.css('visibility', 'visible').animate({ height: ul[0].hei }, { duration: 350, complete: function () { ul.css('overflow', 'visible'); } });
                });
            }
        },
        function () {
            var ul = $('div:first', this);
            if (ul.length) {
                var css = { visibility: 'hidden', height: ul[0].hei };
                ul.stop().retarder(0, function (i) { i.css(css); });

            }
        }
    );

    $('ul ul li', '#hornav').hover(
        function () {
            var ul = $('div:first', this);
            if (ul.length) {
                if (!ul[0].wid) ul[0].wid = ul.width();
                ul.css({ width: 0, overflow: 'hidden' }).retarder(10, function (i) {
                    i.css('visibility', 'visible').animate({ width: ul[0].wid }, { duration: 200, complete: function () { ul.css('overflow', 'visible'); } });
                });
            }
        },
        function () {
            var ul = $('div:first', this);
            if (ul.length) {
                var css = { visibility: 'hidden', width: ul[0].wid };
                ul.stop().retarder(0, function (i) { i.css(css); });
            }
        }
    );
});

//product item gallery

$(function () {
    $(".scrollable").scrollable();
    $(".items img").click(function () {
        if ($(this).hasClass("active")) { return; }
        var url = $(this).attr("alt");
        var wrap = $("#image_wrap").fadeTo("medium", 0.5);
        var img = new Image();
        img.onload = function () {
            wrap.fadeTo("fast", 1);
            wrap.find("img").attr("src", url);
        };

        img.src = url;
        $(".items img").removeClass("active");
        $(this).addClass("active");
    }).filter(":first").click();
});


//filter portfolio js

(function ($) {
    $.fn.sorted = function (customOptions) {
        var options = {
            reversed: false,
            by: function (a) {
                return a.text();
            }
        };
        $.extend(options, customOptions);

        $data = $(this);
        arr = $data.get();
        arr.sort(function (a, b) {

            var valA = options.by($(a));
            var valB = options.by($(b));
            if (options.reversed) {
                return (valA < valB) ? 1 : (valA > valB) ? -1 : 0;
            } else {
                return (valA < valB) ? -1 : (valA > valB) ? 1 : 0;
            }
        });
        return $(arr);
    };

})(jQuery);


// filter and sort portfolio
$(function () {

    var read_button = function (class_names) {
        var r = {
            selected: false,
            type: 0
        };
        for (var i = 0; i < class_names.length; i++) {
            if (class_names[i].indexOf('selected-') == 0) {
                r.selected = true;
            }
            if (class_names[i].indexOf('segment-') == 0) {
                r.segment = class_names[i].split('-')[1];
            }
        };
        return r;
    };

    var determine_sort = function ($buttons) {
        var $selected = $buttons.parent().filter('[class*="selected-"]');
        return $selected.find('a').attr('data-value');
    };

    var determine_kind = function ($buttons) {
        var $selected = $buttons.parent().filter('[class*="selected-"]');
        return $selected.find('a').attr('data-value');
    };

    var $preferences = {
        duration: 800,
        easing: 'easeInOutQuad',
        adjustHeight: true
    };

    var $list = $('#list');
    var $data = $list.clone();

    var $controls = $('ul.splitter ul');

    $controls.each(function (i) {

        var $control = $(this);
        var $buttons = $control.find('a');

        $buttons.bind('click', function (e) {

            var $button = $(this);
            var $button_container = $button.parent();
            var button_properties = read_button($button_container.attr('class').split(' '));
            var selected = button_properties.selected;
            var button_segment = button_properties.segment;

            if (!selected) {

                $buttons.parent().removeClass('selected-0').removeClass('selected-1').removeClass('selected-2');
                $button_container.addClass('selected-' + button_segment);

                var sorting_type = determine_sort($controls.eq(1).find('a'));
                var sorting_kind = determine_kind($controls.eq(0).find('a'));

                if (sorting_kind == 'all') {
                    var $filtered_data = $data.find('li');
                } else {
                    var $filtered_data = $data.find('li.' + sorting_kind);
                }

                if (sorting_type == 'date') {
                    var $sorted_data = $filtered_data.sorted({
                        by: function (v) {
                            return Date.parse($(v).find('span').text());
                        }
                    });
                } else {
                    var $sorted_data = $filtered_data.sorted({
                        by: function (v) {
                            return $(v).find('strong').text().toLowerCase();
                        }
                    });
                }

                $list.quicksand($sorted_data, $preferences);

            }

            e.preventDefault();
        });

    });

    var high_performance = true;
    var $performance_container = $('#performance-toggle');
    var $original_html = $performance_container.html();

    $performance_container.find('a').live('click', function (e) {
        if (high_performance) {
            $preferences.useScaling = false;
            $performance_container.html('CSS3 scaling turned off. Try the demo again. <a href="#toggle">Reverse</a>.');
            high_performance = false;
        } else {
            $preferences.useScaling = true;
            $performance_container.html($original_html);
            high_performance = true;
        }
        e.preventDefault();
    });
});