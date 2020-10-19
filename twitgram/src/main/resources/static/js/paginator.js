function createPaginator(obj) {
    let paginator = document.getElementById(obj.idPaginator);
    let ul = document.createElement("ul");
    paginator.appendChild(ul);
    ul.setAttribute("class", obj.ulClass);
    ul.setAttribute("id", "paginator-container");
    if (obj.noFirstPage == undefined) {
        obj.noFirstPage = 1;
    }
    
    if (obj.firstPage) {
        if (obj.noActualPage > obj.noFirstPage) {
            createPage(obj.firstPage, ul, "firstPage", obj.noFirstPage);
        }
    }
    if (obj.previousPage) {
        if (obj.noActualPage > obj.noFirstPage) {
            createPage(obj.previousPage, ul, "previousPage", obj.noActualPage - 1);
        }
    }
    let from, to;
    if (obj.totalPages <= obj.noElementsPerPage) {
        from = obj.noFirstPage;
        to = obj.totalPages;
    } else {
        if (obj.noActualPage <= obj.noElementsPerPage / 2) {
            from = obj.noFirstPage;
            to = obj.noElementsPerPage;
        } else if (obj.noActualPage >= obj.totalPages - (obj.noElementsPerPage / 2)) {
            from = obj.totalPages - obj.noElementsPerPage + 1;
            to = obj.totalPages;
        } else {
            from = obj.noActualPage - (parseInt(obj.noElementsPerPage / 2));
            to = obj.noActualPage + (parseInt(obj.noElementsPerPage / 2));
        }
    }

    for (let i = from; i <= to; i++) {
        if (obj.noActualPage == i) {
            createPage(obj.actualPage, ul, "page_" + i, i);
        } else {
            createPage(obj.normalPage, ul, "page_" + i, i);
        }
    }

    if (obj.nextPage) {
        if (obj.noActualPage < obj.totalPages) {
            createPage(obj.nextPage, ul, "nextPage", obj.noActualPage + 1);
        }
    }

    if (obj.lastPage) {
        if (obj.noActualPage < obj.totalPages) {
            createPage(obj.lastPage, ul, "lastPage", obj.totalPages);
        }
    }
}

function createPage(obj, ul, id, noPage) {
    let li = document.createElement("li");
    li.setAttribute("id", id);
    li.setAttribute("class", obj.classPage);
    ul.appendChild(li);
    let a = document.createElement("a");
    li.appendChild(a);
    let linkPage = obj.linkPage.replace("{page}", noPage);
    a.setAttribute("href", linkPage);
    a.setAttribute("class", obj.linkClass);
    a.setAttribute("id", "link" + id);
    if (obj.namePage.includes("{page}")) {
        a.innerHTML = obj.namePage.replace("{page}", noPage);
    } else {
        a.innerHTML = obj.namePage;
    }
    
}