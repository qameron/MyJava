public class Memento {
    private Cell article;

    public Memento(Cell articleSave) {
        article = articleSave; }

    public Cell getSavedArticle() {
        return article; }

}

