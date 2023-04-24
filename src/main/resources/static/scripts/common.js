document
    .addEventListener('DOMContentLoaded', () => {
        // クリックイベントでメニューを開閉
        const $navbarBurgers = Array.prototype.slice.call
        (document.querySelectorAll('.navbar-burger'), 0);
        if ($navbarBurgers.length > 0) {
            $navbarBurgers.forEach($el => {
                $el.addEventListener('click', () => {
                    const target = $el.dataset.target;
                    const $target = document.getElementById(target);
                    $el.classList.toggle('is-active');
                    $target.classList.toggle('is-active');
                });
            });
        }
    });

function activateNav(activeNav) {
    const $navbarItems = Array.prototype.slice.call(document.querySelectorAll('.navbar-item'), 0);
    if ($navbarItems.length > 0) {
        $navbarItems.forEach($el => {
            if ($el.id === activeNav) {
                $el.classList.add('is-active');
            }
        });
    }
}

/**
 * 一覧の行をクリックして情報ページに遷移
 * @param rowClassName
 * @param detailUrl
 */
function addDetailLinkToRow(rowClassName, detailUrl) {
    const $employeeRows = Array.prototype.slice.call(document.querySelectorAll(rowClassName), 0);
    if ($employeeRows.length > 0) {
        $employeeRows.forEach($el => {
            $el.addEventListener('click', () => {
                const rowDataId = $el.getAttribute('data-id');
                window.location.href = detailUrl + '/' + rowDataId;
            });
        });
    }
}
