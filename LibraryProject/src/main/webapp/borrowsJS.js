const servletUrl = '/borrows/';

async function loadBorrowings() {
    try {
        const response = await fetch(servletUrl);

        if (!response.ok) throw new Error('Network response was not ok');
        const borrowings = await response.json();

        const borrowingList = document.querySelector('#borrowingsDiv ul');
        borrowings.forEach(borrowing => {
            const li = document.createElement('li');
            li.innerHTML = `
                ${borrowing.bookCode} - ${borrowing.name}<br>
                [${formatDate(borrowing.borrowDate)} - ${formatDate(borrowing.returnDate)}]
            `;
            borrowingList.appendChild(li);
        });

    } catch (error) {
        console.error('Error fetching borrowings:', error);
    }
}

function formatDate(dateStr) {
    if (!dateStr) return "Not Returned";

    const date = new Date(dateStr);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();

    return `${day}.${month}.${year}`;
}

loadBorrowings();