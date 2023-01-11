package ru.job4j.softaria;

import java.util.Objects;

public class Website {
    private String url;
    private String html;

    /**
     * Модель данных для представления веб-сайтов в виде url-html.
     */

    public Website(String url, String html) {
        this.url = url;
        this.html = html;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Website websites = (Website) o;
        return Objects.equals(url, websites.url) && Objects.equals(html, websites.html);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, html);
    }

    @Override
    public String toString() {
        return "Websites{"
                + "url='" + url + '\''
                + ", html='" + html + '\''
                + '}';
    }
}
