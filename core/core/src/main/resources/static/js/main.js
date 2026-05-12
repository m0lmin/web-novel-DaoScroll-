document.addEventListener("DOMContentLoaded", () => {
    // 1. Відновлення теми з кешу браузера
    const savedTheme = localStorage.getItem("theme") || "light";
    document.documentElement.setAttribute("data-theme", savedTheme);
});

// Функція для зміни теми (Світла/Темна)
function toggleTheme() {
    const currentTheme = document.documentElement.getAttribute("data-theme");
    const newTheme = currentTheme === "light" ? "dark" : "light";

    document.documentElement.setAttribute("data-theme", newTheme);
    localStorage.setItem("theme", newTheme);
}

// Універсальна функція виходу з акаунту
function logout() {
    localStorage.removeItem('userId');
    alert('Ви вийшли з акаунту!');
    window.location.href = "index.html";
}