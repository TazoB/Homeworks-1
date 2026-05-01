async function registerUser() {
        const username = document.getElementById("regUsername").value;
        const password = document.getElementById("regPassword").value;

        const response = await fetch("/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
        });

        if (response.status === 403) {
            alert("User already exists!");
        } else {
            alert("User registered!");
        }
    }

    async function sendMessage() {
        const username = document.getElementById("targetUser").value;
        const message = document.getElementById("messageText").value;

        const response = await fetch("/message", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `username=${encodeURIComponent(username)}&message=${encodeURIComponent(message)}`
        });

        if (response.status === 403) {
            alert("Error: user not found or invalid message");
        } else {
            alert("Message sent!");
        }
    }

    async function loadMessages() {
        const username = document.getElementById("loginUsername").value;
        const password = document.getElementById("loginPassword").value;

        const response = await fetch(`/message?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`);

        if (response.status === 403) {
            alert("Invalid credentials");
            return;
        }

        const messages = await response.json();

        const list = document.getElementById("messages");
        list.innerHTML = "";

        messages.forEach(msg => {
            const li = document.createElement("li");
            li.textContent = msg.content;
            list.appendChild(li);
        });
    }