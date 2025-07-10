Debut d'un serveur mcp avec un client mcp avec un AI AGENT 

+-------------------+               HTTP (REST)               +-------------------+
|                   | <-------------------------------------> |                   |
|  MCP Client (UI)  |                                         |  MCP Server       |
| (React, Postman,  |                                         | (Spring Boot API) |
|  ou autre Front)  |                                         |                   |
+-------------------+                                         +---------+---------+
                                                                         |
                                                                         | Appels internes (Service)
                                                                         v
                                                              +----------+----------+
                                                              |                     |
                                                              |  AI Agent (Service) | ---> LLM OLLAMA
                                                              |  (Spring Bean)      |
                                                              |                     |
                                                              +---------------------+
