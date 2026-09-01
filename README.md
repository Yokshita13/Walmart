# Walmart Shipping Data Processor

A Python-based data processing project developed as part of the **Walmart USA Advanced Software Engineering Virtual Experience Program on Forage**.

The project focuses on processing shipping data provided in multiple CSV spreadsheets and transforming it into a structured SQLite database.

## Project Overview

The shipping department provides data across multiple spreadsheets, where each file contains different parts of the shipment information.

The Python script:

- Reads shipping data from CSV files.
- Extracts relevant shipment and product information.
- Combines data from dependent spreadsheets using a shipment identifier.
- Groups identical products within a shipment.
- Calculates the quantity of each product.
- Inserts products and shipment records into a SQLite database.
- Uses database transactions to prevent partial updates when an error occurs.

## Technologies Used

- **Python**
- **SQLite**
- **CSV**
- **SQL**
- **Git & GitHub**

##  Data Processing Workflow

```text
CSV Spreadsheets
       ↓
Read & Extract Data
       ↓
Match Shipment Information
       ↓
Group Products by Shipment
       ↓
Calculate Product Quantities
       ↓
Transform Data
       ↓
SQLite Database
