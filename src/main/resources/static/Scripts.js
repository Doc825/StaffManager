const CURRENT_URL = window.location.host;
function changeLanguage(link) {
    window.location.href = 'http://' + CURRENT_URL + link;
}

function loadEmployees(link) {
    fetch('http://' + CURRENT_URL + link)
        .then(response => response.json())
        .then(listOfEmployees => {
            const tbodyElement = document.querySelector('#employeeView');

            if (tbodyElement) {
                listOfEmployees.forEach(function (employee) {
                    const newRowElement = generateNewRow([
                        employee['firstName'],
                        employee['lastName'],
                        employee['departmentName'],
                        employee['jobTitle'],
                        employee['regionName'],
                        employee['countryName'],
                        employee['state'],
                        employee['city']
                    ]);
                    tbodyElement.appendChild(newRowElement);
                });
            } else {
                console.error('Error: tbodyElement is null');
            }
        })
        .catch(error => {
            console.error('Error fetching employee details:', error);
        });
}

function generateNewRow(value) {
    const trElement = document.createElement('tr');
    value.forEach(function (data) {
        const td = document.createElement('td');
        td.textContent = data;
        trElement.appendChild(td);
    });
    return trElement;
}

function cleanEmployees() {
    const tbodyElement = document.querySelector('#employeeView');
    while (tbodyElement.children.length > 1) {
        tbodyElement.removeChild(tbodyElement.lastChild);
    }
}

function findByName(element) {
    let username = document.getElementById(element).value;
    console.log('Searching for:', username);
    loadEmployees('/api/employeeDetails/' + element + '/' + username)
}
function findByRegion(element) {
    let region = document.getElementById(element).value;
    console.log('Searching by region: ', region);
    loadEmployees('/api/regions/' + region)
}
function findByCity(element) {
    let city = document.getElementById(element).value;
    console.log('Searching by city: ', city);
    loadEmployees('api/locations/' + city)
}

function clearInput(element) {
    document.getElementById(element).value = '';
}


function addNewUser() {
    let newUser = {
        "id": $("#newId").val(),
        "address": $("#newAddress").val(),
        "country": $("#country-selector").val(),
        "birthDate": $("#newBirthDate").val() + " 00:00:00",
        "firstname": $("#newFirstName").val(),
    }

    let options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(newUser)
    }

    fetch('http://' + CURRENT_URL + "/citizens", options)
        .then(response => response.json())
        .then(listOfCitizens => {
            console.log("user was added! " + listOfCitizens)
        }).catch(error => {
        console.log("user not added! " + error)
    })
}

function getAllCountries() {
    console.log("all countries list")
    fetch(
        "http://" + CURRENT_URL + "/countries"
    )
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then(function getAllCountries(countries) {
            const countriesList = document.getElementById("country-selector");
            const sortedByAlphabetCountries = countries.sort();
            for (let index in sortedByAlphabetCountries) {
                let option = document.createElement("option");
                option.setAttribute("value", sortedByAlphabetCountries[index]);
                let optionText = document.createTextNode(sortedByAlphabetCountries[index]);
                option.appendChild(optionText);
                countriesList.appendChild(option);
            }
            return countriesList;
        });
}
function changeTheme() {
    let link = document.getElementById("theme");
    if (link.href.includes("styles.css")) {
        link.href= "darkThemeStyle.css";
    }
    else {
        link.href = "styles.css"
    }
}