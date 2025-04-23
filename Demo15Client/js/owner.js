$(document).ready(function () {
    const savedTheme = localStorage.getItem('theme') || 'dark';
    document.body.dataset.theme = savedTheme;
    function loadTable() {
        $("#loader").show();
        $.ajax({
            url: "http://localhost:8080/owners",
        }).then(function (data) {
            $("#loader").hide();
            $("#owner-count").text(`There are ${data.length} pet owners.`);
            const tableContainer = $("#table-container");
            tableContainer.empty();
            if (data.length > 0) {
                const table = $("<table></table>");
                const thead = $("<thead><tr></tr></thead>");
                const tbody = $("<tbody></tbody>");
                const headers = ["Owner Id", "Name", "Gender", "City", "State", "Mobile Number", "Email Id", "Pet Id", "Pet Name", "Pet Gender", "Pet Type", "Pet Date of Birth", "Pet Place of Birth"];
                headers.forEach(label => {
                    const th = $("<th>").text(label);
                    thead.find("tr").append(th);
                });
                data.forEach(item => {
                    const row = $("<tr></tr>").append(
                        $("<td>").text(item.id),
                        $("<td>").text(item.firstName + " " + item.lastName),
                        $("<td>").text(item.gender),
                        $("<td>").text(item.city),
                        $("<td>").text(item.state),
                        $("<td>").text(item.mobileNumber),
                        $("<td>").text(item.emailId),
                        $("<td>").text(item.petDTO?.id),
                        $("<td>").text(item.petDTO?.name),
                        $("<td>").text(item.petDTO?.gender),
                        $("<td>").text(item.petDTO?.type),
                        $("<td>").text(item.petDTO?.birthDate || "-"),
                        $("<td>").text(item.petDTO?.birthPlace || "-")
                    );
                    tbody.append(row);
                });
                table.append(thead).append(tbody);
                tableContainer.append(table);
            }
        }).catch(function (error) {
            $("#loader").hide();
            $("#owner-count").text("Failed to load data. Please try again later.");
            console.error("Error loading data:", error);
        });
    }
    loadTable();
});
