package org.tinos.emotion.estimation;

public class EmotionSample{
	public double getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(double positiveCount) {
		this.positiveCount = positiveCount;
	}
	public double getNegativeCount() {
		return negativeCount;
	}
	public void setNegativeCount(double negativeCount) {
		this.negativeCount = negativeCount;
	}
	public double getEmotionRatio() {
		return emotionRatio;
	}
	public void setEmotionRatio(double emotionRatio) {
		this.emotionRatio = emotionRatio;
	}
	public double getMotivationRatio() {
		return motivationRatio;
	}
	public void setMotivationRatio(double motivationRatio) {
		this.motivationRatio = motivationRatio;
	}
	public double getCorrelationRatio() {
		return correlationRatio;
	}
	public void setCorrelationRatio(double correlationRatio) {
		this.correlationRatio = correlationRatio;
	}
	public double getContinusRatio() {
		return continusRatio;
	}
	public void setContinusRatio(double continusRatio) {
		this.continusRatio = continusRatio;
	}
	public double getTrendsRatio() {
		return trendsRatio;
	}
	public void setTrendsRatio(double trendsRatio) {
		this.trendsRatio = trendsRatio;
	}
	public double getPredictionRatio() {
		return predictionRatio;
	}
	public void setPredictionRatio(double predictionRatio) {
		this.predictionRatio = predictionRatio;
	}
	public double getGuessRatio() {
		return guessRatio;
	}
	public void setGuessRatio(double guessRatio) {
		this.guessRatio = guessRatio;
	}
	public double getSensingRatio() {
		return sensingRatio;
	}
	public void setSensingRatio(double sensingRatio) {
		this.sensingRatio = sensingRatio;
	}
	public void setMedCount(int frequency) {
		this.medCount = frequency;
	}
	public double getMedCount() {
		return medCount;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	public String getTrending() {
		return trending;
	}
	public void setTrending(String trending) {
		this.trending = trending;
	}
	public String getPrediction() {
		return prediction;
	}
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	public void setMedCount(double medCount) {
		this.medCount = medCount;
	}

	double positiveCount;
	double medCount;
	double negativeCount;
	double emotionRatio;
	double motivationRatio;
	double correlationRatio;
	double continusRatio;
	double trendsRatio;
	double predictionRatio;
	double guessRatio;
	double sensingRatio;
	String motivation;
	String trending;
	String prediction;
}