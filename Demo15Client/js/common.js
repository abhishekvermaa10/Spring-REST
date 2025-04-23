function toggleTheme() {
    const body = document.body;
    const currentTheme = body.dataset.theme;
    const newTheme = currentTheme === "dark" ? "light" : "dark";
    body.dataset.theme = newTheme;
    localStorage.setItem("theme", newTheme);
}
