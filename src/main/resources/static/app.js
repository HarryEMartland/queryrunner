$(function () {

    $.ajaxSetup({
        contentType: "application/json; charset=utf-8"
    });

    var loadingIcon = $('<span class="glyphicon glyphicon-refresh spinning" style="color:grey"></span>');

    var queries = [];
    var argumentList = [];

    var qeuryPromise = $.get('/query')
        .then(function (data) {
            $(data).each(function (i, element) {
                var query = new Query(element.name, element.displayName, element.dependencies);
                queries.push(query);
            });
            return queries;
        });

    var argumentPromise = $.get('/argument')
        .then(function (data) {
            data.sort(function (argumenta, argumentb) {
                return argumenta.order - argumentb.order;
            });
            $(data).each(function (i, element) {
                var argument = new Argument(element.name, element.displayName, element.htmlElement, element.order);
                argumentList.push(argument);
            })
        });

    $.when(qeuryPromise, argumentPromise).done(function () {
        $(queries).each(function (i, query) {
            query.argumentChanged(null, null, true)
        })
    });

    function Query(name, displayName, dependencies) {
        var addedToDom = false;
        var resultWrapper = $('<div class="pull-right"></div>');
        var well = $('<div class="well well-sm"></div>');
        var wrapper = $('<div class="col-sm-4"></div>')
            .append(well);
        well.append(displayName).append(resultWrapper);

        function argumentChanged(argument, value, forceReload) {
            if (dependencies.includes(argument) || forceReload) {
                if (allDependenciesSet()) {

                    if (!addedToDom) {
                        wrapper.prependTo('#loading-queries');
                    }

                    resultWrapper.empty().append(loadingIcon);
                    return $.post('query/' + name, JSON.stringify(argumentList), function (data) {
                        resultWrapper.empty().append(data.displayValue);
                        wrapper.prependTo('#' + data.value + '-queries');
                        addedToDom = true;
                    });
                } else {
                    wrapper.remove()
                }
            }
        }

        function allDependenciesSet() {
            var allSet = true;
            $(argumentList).each(function (i, argument) {
                if (dependencies.includes(argument.name) && !argument.value) {
                    allSet = false;
                }
            });
            return allSet;
        }

        return {
            argumentChanged: argumentChanged
        }

    }

    function Argument(name, displayName, htmlElement, order) {

        var This = this;
        this.name = name;
        this.value = getParameterByName(name);
        this.order = order;
        var input = $(htmlElement).val(this.value);
        var inputGroup = $('' +
            '<div class="input-group">' +
            '<div class="input-group-addon">' + displayName + '</div>' +
            '</div>').append(input);
        var formGroup = $('<div class="form-group col-sm-4"></div>').append(inputGroup);


        $('#argument-container').append(formGroup);

        input.on('change', function () {
            This.value = input.val();
            history.pushState({}, null, updateQueryString(name, This.value));
            $(queries).each(function (i, query) {
                query.argumentChanged(name, input.val());
            })
        });

    }

    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    function updateQueryString(key, value, url) {
        if (!url) url = window.location.href;
        var re = new RegExp("([?&])" + key + "=.*?(&|#|$)(.*)", "gi"),
            hash;

        if (re.test(url)) {
            if (typeof value !== 'undefined' && value !== null)
                return url.replace(re, '$1' + key + "=" + value + '$2$3');
            else {
                hash = url.split('#');
                url = hash[0].replace(re, '$1$3').replace(/(&|\?)$/, '');
                if (typeof hash[1] !== 'undefined' && hash[1] !== null)
                    url += '#' + hash[1];
                return url;
            }
        }
        else {
            if (typeof value !== 'undefined' && value !== null) {
                var separator = url.indexOf('?') !== -1 ? '&' : '?';
                hash = url.split('#');
                url = hash[0] + separator + key + '=' + value;
                if (typeof hash[1] !== 'undefined' && hash[1] !== null)
                    url += '#' + hash[1];
                return url;
            }
            else
                return url;
        }
    }
});