var count = 1;

function addNewRecord() {
    var table = document.getElementById("myTable")
    var tbodyRef = document.getElementsByTagName("tbody")[0]
    var bookName = table.lastElementChild.lastElementChild?.firstElementChild?.nextElementSibling?.innerHTML || "Book 0"
    var lastIndex = bookName.split(" ")[1]

    var trNode = document.createElement("tr")

    var trCheckBoxCell = document.createElement("td")
    trCheckBoxCell.innerHTML = '<input id="checkbox" type="checkbox" onClick="onCheckBoxClick(this)"/>'

    var trBookCell = document.createElement("td");
    trBookCell.innerHTML = "Book " + (parseInt(lastIndex) + 1)

    var trAuthorCell = document.createElement("td")
    trAuthorCell.innerHTML = "Author "+ (parseInt(lastIndex) + 1)

    trNode.appendChild(trCheckBoxCell)
    trNode.appendChild(trBookCell)
    trNode.appendChild(trAuthorCell)

    tbodyRef.appendChild(trNode)
}

function onCheckBoxClick(checkbox) {
    var rowSelect = checkbox.parentElement.parentElement;

    if(checkbox.checked)  {
        rowSelect.style.backgroundColor = "Wheat"

        var deleteButton = document.createElement("td")
        deleteButton.setAttribute("id", "deleted")

        deleteButton.innerHTML = '<button id="delete" type="button" onclick = "deleteRow(this)"> Delete </button>';
        rowSelect.appendChild(deleteButton)
    }
    else{
        rowSelect.style.backgroundColor = "#fff"
        rowSelect.deleteCell(3)
    }
}

function deleteRow(rowObject) {
    var tr = rowObject.parentElement.parentElement;
    document.getElementById("myTable").deleteRow(tr.rowIndex)
    count--;
    alert("Row Deleted Successfully")
}
