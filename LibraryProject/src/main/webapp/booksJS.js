const servletUrl = '/books/'; 

async function loadBooks() {
    try {
        const response = await fetch(servletUrl);
        
        if (!response.ok) throw new Error('Network response was not ok');
        const books = await response.json();
       
        const bookList = document.querySelector('#booksDiv ul');
        books.forEach(book => {
            const li = document.createElement('li');
            li.textContent = `${book.title} by ${book.author} (Code: ${book.code})`;
            
            bookList.appendChild(li);
        });

    } catch (error) {
        console.error('Error fetching books:', error);
    }
}

loadBooks();