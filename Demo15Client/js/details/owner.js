$(document).ready(function () {
    const savedTheme = localStorage.getItem('theme') || 'dark';
    document.body.dataset.theme = savedTheme;
    let currentPage = 0;
    let pageSize = parseInt($("#page-size").val());

    function loadTable(page) {
        $("#loader").show();
        $.ajax({
            url: "http://localhost:8080/owners/details",
            data: { page: page, size: pageSize },
        }).then(function (data) {
            $("#loader").hide();
            $("#owner-count").text(`There are ${data.totalElements} pet owners.`);
            $("#current-page").text(`Page ${data.number + 1} of ${data.totalPages}`);
            $("#prev-page").prop("disabled", data.first);
            $("#next-page").prop("disabled", data.last);
            const tableContainer = $("#table-container");
            tableContainer.empty();
            if (data.content.length > 0) {
                const table = $("<table></table>");
                const thead = $("<thead><tr></tr></thead>");
                const tbody = $("<tbody></tbody>");
                const headers = ["Owner Id", "First Name", "Last Name", "Pet Name"];
                headers.forEach(label => {
                    const th = $("<th>").text(label);
                    thead.find("tr").append(th);
                });
                data.content.forEach(item => {
                    const row = $("<tr></tr>").append(
                        $("<td>").text(item.id),
                        $("<td>").text(item.firstName),
                        $("<td>").text(item.lastName),
                        $("<td>").text(item.petName)
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

    $("#prev-page").click(() => {
        if (currentPage > 0) {
            currentPage--;
            loadTable(currentPage);
        }
    });

    $("#next-page").click(() => {
        currentPage++;
        loadTable(currentPage);
    });

    $("#page-size").change(function () {
        pageSize = parseInt($(this).val());
        currentPage = 0;
        loadTable(currentPage);
    });

    loadTable(currentPage);
});
