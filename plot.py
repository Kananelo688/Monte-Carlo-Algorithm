"""
Python script that reads measured data and plots the graphs
"""
import matplotlib.pyplot as plt
import numpy as np
#Sequential Cut-off data
cut_off4,speedupC4=np.loadtxt("data/cutoff_data_4Core.txt",delimiter=" ",unpack=True)
cut_off8,speedupC8=np.loadtxt("data/cutoff_data_8Core.txt",delimiter=" ",unpack=True)
#Grid_size data
grid4,speedupG4=np.loadtxt("data/Grid_Size_data_4Core.txt",delimiter=" ",unpack=True)
grid8,speedupG8=np.loadtxt("data/Grid_Size_data_8Core.txt",delimiter=" ",unpack=True)
#seach density
density4,speedupS4=np.loadtxt("data/Search_density_data_4Core.txt",delimiter=" ",unpack=True)
density8,speedupS8=np.loadtxt("data/Search_density_data_8Core.txt",delimiter=" ",unpack=True)

figure,axis=plt.subplots(2,2, sharey=True)

axis[0,0].plot(cut_off4,speedupC4,label="Quad-Core_Processor",marker="*",ms=5,color='blue')
axis[0,0].plot(cut_off8,speedupC8,label="Octa-Core_Processor",marker="s",ms=5,color='green')
axis[0,0].set_title("SpeedUp Vs sequential cut-off")
axis[0,0].set_ylabel("SpeedUp")
axis[0,0].set_xlabel("Sequential Cut-off")
axis[0,0].legend()

axis[1,0].plot(grid4,speedupG4,label="Quad-Core_Processor",marker="*",ms=5,color='blue')
axis[1,0].plot(grid8,speedupG8,label="Octa-Core_Processor",marker="s",ms=5,color='green')
axis[1,0].set_title("SpeedUp Vs The Size of the grid")
axis[1,0].set_ylabel("SpeedUp")
axis[1,0].set_xlabel("Grid-Size(rows x cols)")
axis[1,0].legend()

axis[0,1].plot(density4,speedupS4,label="Quad-Core_Processor",marker="*",ms=5,color='blue')
axis[0,1].plot(density8,speedupS8,label="Octa-Core_Processor",marker="s",ms=5,color='c')
axis[0,1].set_title("SpeedUp Vs Search densities")
axis[0,1].set_ylabel("SpeedUp")
axis[0,1].set_xlabel("Search density")
axis[0,1].legend()

axis[1,1].plot([0,1,2,3,4,5,6],[2,4,6,8,10,12,14],label="Octa-Core_Processor",marker="*",ms=5,color='blue')
axis[1,1].plot([0,1,2,3,4,5,6],[1,2,3,4,5,6,7],label="Quad-Core_Processor",marker="*",ms=5,color='red')
axis[1,1].set_title("Expected behaviour")
axis[1,1].set_ylabel("SpeedUp")
axis[1,1].set_xlabel("Sequential Cut-off/search densities/ grid-size")
axis[1,1].legend()
figure.tight_layout()
plt.legend()
plt.show()