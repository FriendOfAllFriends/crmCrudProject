<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Customers</title>

    <link
            type="text/css"
            rel="stylesheet"
            href="${pageContext.request.contextPath}/resources/css/style.css"/>



</head>

<body>

    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

        <input type="button" value="Add Customer"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"/>

        <form:form action="search" method="GET">
            Search customer: <input type="text" name="searchName" />

            <input type="submit" value="Search" class="add-button" />
        </form:form>


            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="tempCustomer" items="${customers}">

                    <!-- get customer id for update link -->
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>

                    <!-- get customer id for delete link -->
                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>

                    <tr>
                        <td><c:out value="${tempCustomer.firstName}"></c:out></td>
                        <td><c:out value="${tempCustomer.lastName}"></c:out></td>
                        <td><c:out value="${tempCustomer.email}"></c:out></td>

                        <td>
                            <!-- update link -->
                            <a href="${updateLink}">Update</a>
                            |
                            <!-- delete link -->
                            <a href="${deleteLink}"
                               onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">
                                Delete</a>
                        </td>
                    </tr>

                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>