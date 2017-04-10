# Tarql: SPARQL for Tables

Tarql is a command-line tool for converting CSV files to RDF using SPARQL 1.1 syntax. It's written in Java and based on Apache ARQ.

**See http://tarql.github.io/ for documentation.**

## Building

Get the code from GitHub: http://github.com/tarql/tarql

Tarql uses Maven. To create executable scripts for Windows and Unix in `/target/appassembler/bin/tarql`:

    mvn package appassembler:assemble

Otherwise it's standard Maven.

# HDT Fork

Note: This fork includes support to generate RDF/HDT files as output.

## Building

Make sure you install the latest hdt-java 2.1-SNAPSHOT before compiling Tarql.

## Usage

1. Download geeky CSV file:

`$ wget https://raw.githubusercontent.com/datasets/s-and-p-500-companies/master/data/constituents-financials.csv

2. Write typical select all query to see what's in there in file `all.sparql`:

```SPARQL
SELECT * WHERE {} LIMIT 50
```

3. Run and see what is in the CSV file:

`$ tarql all.sparql constituents-financials.csv`

4. Write final Tarql query into file `query.sparql`

```SPARQL
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX nas: <http://nasdaq.com/symbol/>

CONSTRUCT {
  ?uri <http://example.org/price> ?tprice .
} WHERE {
  BIND(URI(concat("http://nasdaq.com/symbol/",?Symbol)) AS ?uri)
  BIND(xsd:decimal(?Price) AS ?tprice)
}
```

5. Run tarql and generate output HDT file:

`$ tarql -base https://example.org/constituents-financials.csv --hdt out.hdt query.sparql constituents-financials.csv`
