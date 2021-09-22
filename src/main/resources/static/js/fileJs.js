window.addEventListener('DOMContentLoaded', () => {
    function init() {
        let request = new XMLHttpRequest();
        request.open('GET', '/winners/');
        request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        request.send();//сюда мы можем боди

        var count = 1;

        request.addEventListener('readystatechange', function () {
            if (request.readyState == 4 && request.status == 200) {
                let data = JSON.parse(request.response);
                data.forEach(item => {
                    let card = document.createElement('tr');

                    card.innerHTML = `
                        <th>${count}</th>
                        <th>${item.player.name}</th>
                        <th>${item.score}</div>
                   `;
                    document.querySelector('.table').appendChild(card);
                    count++;
                });
                // console.log(data);
            } else {
                console.error("Что-то пошло не так");
            }

        });
    }

    init();


})