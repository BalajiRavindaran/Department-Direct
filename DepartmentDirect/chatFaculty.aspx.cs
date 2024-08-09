using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.UI;
using Newtonsoft.Json;

namespace DepartmentDirect
{
    public partial class chatFaculty : System.Web.UI.Page
    {
        private readonly string apiUrl = "http://3.128.202.148:9090/departmentdirect/qa/list";
        private List<MessageResponse> allMessages;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadMessages();
            }
        }

        private void LoadMessages()
        {
            string studentId = Session["StudentId"]?.ToString();
            string departmentId = Session["DepartmentId"]?.ToString(); // Or retrieve it from session or other source if needed

            if (string.IsNullOrEmpty(studentId))
            {
                Literal1.Text = "<p>Student ID is missing. Please log in again.</p>";
                return;
            }

            using (HttpClient client = new HttpClient())
            {
                try
                {
                    // Construct the URL with query parameters
                    string url = $"{apiUrl}?StudentID={studentId}&DepartmentID={departmentId}";

                    // Make the GET request
                    HttpResponseMessage response = client.GetAsync(url).Result;
                    if (response.IsSuccessStatusCode)
                    {
                        string responseString = response.Content.ReadAsStringAsync().Result;
                        allMessages = JsonConvert.DeserializeObject<List<MessageResponse>>(responseString);

                        if (allMessages != null && allMessages.Any())
                        {
                            DisplayMessages();
                        }
                        else
                        {
                            Literal1.Text = "<p>No messages found.</p>";
                        }
                    }
                    else
                    {
                        Literal1.Text = $"<p>Failed to load messages. Status code: {response.StatusCode}</p>";
                    }
                }
                catch (Exception ex)
                {
                    Literal1.Text = $"<p>Failed to load messages: {ex.Message}</p>";
                }
            }
        }

        private void DisplayMessages()
        {
            string role = Session["Role"] as string;

            if (allMessages == null || !allMessages.Any())
            {
                ChatContent.Text = "<p>No messages available.</p>";
                return;
            }

            // Reverse the order of messages
            var reversedMessages = allMessages.AsEnumerable().Reverse().ToList();

            var messageHtml = "";
            foreach (var message in reversedMessages)
            {
                string formattedDateTime = DateTime.Parse(message.DateTime).ToString("MMMM d, yyyy hh:mm:ss tt");
                messageHtml += $"<div class='message {role}'>{formattedDateTime}<br>{message.Message}</div>";
            }

            ChatContent.Text = messageHtml;
        }


        protected void SendMessage_Click(object sender, EventArgs e)
        {
            string studentId = Session["StudentId"]?.ToString();
            string departmentId = Session["DepartmentId"]?.ToString(); ; // Or retrieve it from session or other source if needed
            string message = TextBox1.Text;

            if (string.IsNullOrEmpty(studentId))
            {
                Literal1.Text = "<p>Student ID is missing. Please log in again.</p>";
                return;
            }

            if (string.IsNullOrEmpty(message))
            {
                Literal1.Text = "<p>Message cannot be empty.</p>";
                return;
            }

            using (HttpClient client = new HttpClient())
            {
                try
                {
                    // Prepare form data
                    var formData = new Dictionary<string, string>
            {
                { "StudentID", studentId },
                { "DepartmentID", departmentId },
                { "Message", message },
                { "Category", "None" }
            };

                    var content = new FormUrlEncodedContent(formData);

                    HttpResponseMessage response = client.PostAsync("http://3.128.202.148:9090/departmentdirect/qa/create", content).Result;

                    if (response.IsSuccessStatusCode)
                    {
                        TextBox1.Text = ""; // Clear the textbox after sending the message
                        LoadMessages(); // Reload messages to include the new message
                        Literal1.Text = "";
                    }
                    else
                    {
                        Literal1.Text = $"<p>Failed to send message. Status code: {response.StatusCode}</p>";
                    }
                }
                catch (Exception ex)
                {
                    Literal1.Text = $"<p>Failed to send message: {ex.Message}</p>";
                }
            }
        }


        private class MessageResponse
        {
            public int Id { get; set; }
            public string StudentId { get; set; }
            public int DepartmentId { get; set; }
            public string Message { get; set; }
            public string DateTime { get; set; }
        }
    }
}
