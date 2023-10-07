package animals;

public abstract class Animal
{
	public String animalName;

	/**
	 * @return Returns this animal's given name.
	 */
	public String getNickname() {
		return animalName;
	}

	/**
	 * Check whether two animals can live together.
	 *
	 * @param animal The animal for which to check compatibility with this animal.
	 * @return Returns true for compatible animals and false otherwise.
	 */
	public abstract boolean isCompatibleWith(Animal animal);
}
