for (let i = 1; i <= 4; i++) {
    $(`#mytable${i} #checkall${i}`).click(function() {
        if ($(`#mytable${i} #checkall${i}`).is(':checked')) {
            $(`#mytable${i} input[type=checkbox]`).each(function() {
                $(this).prop("checked", true);
            });
        } else {
            $(`#mytable${i} input[type=checkbox]`).each(function() {
                $(this).prop("checked", false);
            });
        }
    });
}