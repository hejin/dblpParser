# First, import the client from semanticscholar module
from semanticscholar import SemanticScholar

# You'll need an instance of the client to request data from the API
sch = SemanticScholar()

# Get a paper by its ID
#paper = sch.get_paper('10.1093/mind/lix.236.433')
paper = sch.get_paper('10.1109/TIFS.2023.3318934')

# Print the paper title
print(paper.title)
print(paper.abstract)
