<%@ Page Title="" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="adminAnalytics.aspx.cs" Inherits="DepartmentDirect.adminAnalytics" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div style="width: 80%; margin: auto;">
        <canvas id="categoryChart" width="600" height="300"></canvas>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // Example data from the API response
            const data = [
                { count: 3, category: "Department inquiry", percentage: 4.92 },
                { count: 29, category: "None", percentage: 47.54 },
                { count: 22, category: null, percentage: 36.07 },
                { count: 3, category: "Course Similarities/Differences", percentage: 4.92 },
                { count: 3, category: "Others", percentage: 4.92 },
                { count: 1, category: "Course Pre-Requisite", percentage: 1.64 }
            ];

            // Prepare data for Chart.js
            const labels = data.map(item => item.category || 'Uncategorized');
            const counts = data.map(item => item.count);

            const ctx = document.getElementById('categoryChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar', // You can change this to 'pie', 'doughnut', etc.
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Category Count',
                        data: counts,
                        backgroundColor: 'rgba(190, 192, 75, 0.2)',
                        borderColor: 'rgba(190, 192, 75, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        });
    </script>
</asp:Content>


