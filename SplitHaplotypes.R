# Install or load tidyr and dplyr

if (!require(tidyr, quietly=TRUE)) {
  install.packages("tidyr")
  library(tidyr)
}

if (!require(dplyr, quietly=TRUE)) {
  install.packages("dplyr")
  library(dplyr)
}

# set path
path <- file.path("c:/Users/Stanford/Documents/PostDoc_SBIMB/ShayneHaplotypes")
infile <- file.path(path, "hla.37.phased.xlsx")
#outPaths <- file.path(path,x, ".data.txt")
#dir.create(file.path(path, "patientData"), recursive = T)
hpos <- readxl::read_excel(infile, sheet = 1, n_max = 451)

nameVector <- colnames(hpos)[5:44]

splitAlleles = function(x, infile){
  y <- tolower(x)
  y <- infile %>% select(POS, RSID, REF, x)
  y  <- y %>% separate(x, c("mat","pat"), remove = F)
  outPath <- file.path(path, "patientData", paste(x, "data.txt", sep = '.'))
  write.table(y, outPath, quote = F, sep = ',', row.names = F)
  return(y)
}

lapply(nameVector, splitAlleles, hpos)
