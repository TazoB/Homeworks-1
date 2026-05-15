loadPage(1);

function loadPage(page) {
    fetch(`http://localhost:8080/market/${page}`)
        .then(response => response.json())
        .then(items => {
            const container = document.getElementById("items-grid");
            container.innerHTML = "";
            items.forEach(item => {
                const itemDiv = document.createElement("div");
                itemDiv.className = "item";
                itemDiv.innerHTML = `
                    <h2>${item.name}</h2>
                    <p>Price: $${item.price}</p>
                    <p>ID: ${item.id}</p>
                `;
                container.appendChild(itemDiv);
            });
        }   
        )
        .catch(error =>
            console.error('Error fetching initial page:', error)
        );
}   
        
function goToPreviousPage() {
    const page = Number(document.getElementById("page-number").innerHTML);

    if (page <= 1) {
        return;
    }

    const newPage = page - 1;
    document.getElementById("page-number").innerHTML = newPage;
    loadPage(newPage);
}

function goToNextPage() {
    const page = Number(document.getElementById("page-number").innerHTML);
    const newPage = page + 1;
    document.getElementById("page-number").innerHTML = newPage;
    loadPage(newPage);
}