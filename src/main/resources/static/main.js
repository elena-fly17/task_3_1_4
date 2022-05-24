// отображение таблицы со всеми юзерами
function getTableUsers() {
    fetch("/admin/users").then(
        response => {
            response.json().then(
                users => {
                    if (users.length > 0) {
                        let allUsers = " "

                        users.forEach((u) => {
                            allUsers += `
                                <tr>
                                <td id="id${u.id}">${u.id}</td>
                                <td id="name${u.id}">${u.name}</td>                            
                                <td id="surname${u.id}">${u.surname}</td>                            
                                <td id="age${u.id}">${u.age}</td>                            
                                <td id="email${u.id}">${u.email}</td>                         
                                <td id="role${u.id}">${Array.from(u.roles, element => ' ' + element.name)}</td>
                                <td><div class="btn-group"><button onclick='fillModalEdit(${u.id})' data-target="#editModal"
                                                            data-toggle="modal" class="btn btn-success">Edit</button></div></td>
                                <td><div class="btn-group"><button onclick='fillModalDelete(${u.id})' data-target="#deleteModal"
                                                            data-toggle="modal" class="btn btn-danger">Delete</button></div></td></tr>`
                        })
                        document.getElementById("bodyUsersTable").innerHTML = allUsers
                    }
                })
        }
    )
}
getTableUsers()

// заполнение модалки для редактирования юзера
function fillModalEdit(id) {
    document.getElementById("idEdit").value = id
    document.getElementById("nameEdit").value = $("#name" + id).text()
    document.getElementById("surnameEdit").value = $("#surname" + id).text()
    document.getElementById("ageEdit").value = $("#age" + id).text()
    document.getElementById("emailEdit").value = $("#email" + id).text()
    document.getElementById("passwordEdit").value = $("#password" + id).text()
}

// заполнение модалки для удаления юзера
function fillModalDelete(id) {
    document.getElementById("idDelete").value = id
    document.getElementById("nameDelete").value = $("#name" + id).text()
    document.getElementById("surnameDelete").value = $("#surname" + id).text()
    document.getElementById("ageDelete").value = $("#age" + id).text()
    document.getElementById("emailDelete").value = $("#email" + id).text()
}

// установка ролей после их получения из селектора
function setRoles(arrOfRoles) {
    let roles = []

    if (arrOfRoles.indexOf("ROLE_ADMIN") !== -1) {
        roles.push({id: 2, role: "ROLE_ADMIN"})
        roles.push({id: 1, role: "ROLE_USER"})
    }

    if (arrOfRoles.indexOf("ROLE_USER") !== -1) {
        roles.push({id: 1, role: "ROLE_USER"})
    }
    return roles
}

// прослушка для кнопки в форме для добавления юзера
document.getElementById("formAddUser").addEventListener("submit", addNewUser)

// добавление нового юзера
function addNewUser(e) {
    e.preventDefault()

    let addName = document.getElementById("AddNewUserName").value
    let addUsername = document.getElementById("AddNewUserSurname").value
    let addAge = document.getElementById("AddNewUserAge").value
    let addEmail = document.getElementById("AddNewUserEmail").value
    let addPassword = document.getElementById("AddNewUserPassword").value
    let addRoles = setRoles(Array.from(document.getElementById("AddNewUserRoles").selectedOptions).map(options => options.value))

    fetch("/admin/users", {
        method: "POST",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            name: addName,
            surname: addUsername,
            age: addAge,
            email: addEmail,
            password: addPassword,
            roles: addRoles
        })
    }).finally(() => {
        getTableUsers()
        document.getElementById("formAddUser").reset()
    })
}

// прослушка для кнопки в форме редактирования юзера
document.getElementById("formUpdateUser").addEventListener("submit", updateUser)

function updateUser(e) {
    e.preventDefault()

    let editId = document.getElementById("idEdit").value
    let editName = document.getElementById("nameEdit").value
    let editSurname = document.getElementById("surnameEdit").value
    let editAge = document.getElementById("ageEdit").value
    let editEmail = document.getElementById("emailEdit").value
    let editPassword = document.getElementById("passwordEdit").value
    let editRoles = setRoles(Array.from(document.getElementById("EditUserRoles").selectedOptions).map(option => option.value))

    fetch("/admin/users", {
        method: "PUT",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id: editId,
            name: editName,
            surname: editSurname,
            age: editAge,
            email: editEmail,
            password: editPassword,
            roles: editRoles

        })
    }).finally(() => {
        $("#editModal").modal("hide")
        getTableUsers()
    })
}

// прослушка для кнопки в форме для удаления юзера
document.getElementById("formDeleteUser").addEventListener("submit", deleteUser)

// функция для удаления юзера
function deleteUser(e) {
    e.preventDefault()
    let deleteId = document.getElementById("idDelete").value

    fetch("/admin/users/" + deleteId, {
        method: "DELETE",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id: deleteId
        })
    }).finally(() => {
        $("#deleteModal").modal("hide")
        getTableUsers()
    })
}
