import re

# Define the subfunction to extract DOIs
def extract_doi(url):
    # Define a regex pattern for DOIs
    doi_pattern = r"10\.\d{4,9}/[^\s]+"  # Matches DOIs like 10.xxxx/yyyy [[4]]
    match = re.search(doi_pattern, url)
    return match.group(0) if match else None  # Return the DOI or None if not found

# Input strings
url1 = "https://doi.org/10.1109/ISCA45697.2020.00025"
url2 = "https://dl.acm.org/doi/10.1109/SC41406.2024.00067"

# Process both URLs using the subfunction
doi1 = extract_doi(url1)
doi2 = extract_doi(url2)

print(doi1)  # Output: 10.1109/ISCA45697.2020.00025
print(doi2)  # Output: 10.1109/SC41406.2024.00067
