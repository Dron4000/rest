


let navbarInfo = $('#navBarInfo');
inNavBar()
function inNavBar(){
    fetch("/api/incomingAdmin").then((response) => {
        console.log(response.statusText + response.status)
        if (response.ok) {
            response.json().then((user) => {
                console.log(user)

                let a = document.createElement('a');
                a.setAttribute('class', "navbar-text text-white");
                a.appendChild(document.createTextNode(user.email+ " with roles : " +user.roles.map(roleUser => roleUser.name)))
                navbarInfo.append(a);


            });
        } else {
            console.error(response.statusText + response.status)
        }
    })
}