using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using WebApi.Mappings;

namespace WebApi.Models
{
    public partial class ocorrenciasdbContext : DbContext
    {
        public ocorrenciasdbContext()
        {
        }

        public ocorrenciasdbContext(DbContextOptions<ocorrenciasdbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<AreaAtendimento> AreaAtendimento { get; set; }
        public virtual DbSet<AreaAtuacao> AreaAtuacao { get; set; }
        public virtual DbSet<Cidade> Cidade { get; set; }
        public virtual DbSet<Endereco> Endereco { get; set; }
        public virtual DbSet<Estado> Estado { get; set; }
        public virtual DbSet<EstadoOcorrencia> EstadoOcorrencia { get; set; }
        public virtual DbSet<HistoricoOcorrencia> HistoricoOcorrencia { get; set; }
        public virtual DbSet<HistoricoSenha> HistoricoSenha { get; set; }
        public virtual DbSet<ImagemOcorrencia> ImagemOcorrencia { get; set; }
        public virtual DbSet<ImagemUsuario> ImagemUsuario { get; set; }
        public virtual DbSet<Ocorrencia> Ocorrencia { get; set; }
        public virtual DbSet<PrioridadeOcorrencia> PrioridadeOcorrencia { get; set; }
        public virtual DbSet<TelefoneAreaatendimento> TelefoneAreaatendimento { get; set; }
        public virtual DbSet<TelefoneUsuario> TelefoneUsuario { get; set; }
        public virtual DbSet<TipoOcorrencia> TipoOcorrencia { get; set; }
        public virtual DbSet<Usuario> Usuario { get; set; }
        public virtual DbSet<UsuarioAreaatendimento> UsuarioAreaatendimento { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseNpgsql("Server=localhost;Database=ocorrenciasdb;Port=5432;User Id=postgres;Password=admin;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new AreaAtendimentoMap());
            modelBuilder.ApplyConfiguration(new AreaAtuacaoMap());
            modelBuilder.ApplyConfiguration(new CidadeMap());
            modelBuilder.ApplyConfiguration(new EnderecoMap());
            modelBuilder.ApplyConfiguration(new EstadoOcorrenciaMap());
            modelBuilder.ApplyConfiguration(new HistoricoOcorrenciaMap());
            modelBuilder.ApplyConfiguration(new HistoricoSenhaMap());
            modelBuilder.ApplyConfiguration(new ImagemOcorrenciaMap());
            modelBuilder.ApplyConfiguration(new ImagemUsuarioMap());
            modelBuilder.ApplyConfiguration(new OcorrenciaMap());
            modelBuilder.ApplyConfiguration(new PrioridadeOcorrenciaMap());
            modelBuilder.ApplyConfiguration(new TelefoneAreaAtendimentoMap());
            modelBuilder.ApplyConfiguration(new TelefoneUsuarioMap());
            modelBuilder.ApplyConfiguration(new TipoOcorrenciaMap());
            modelBuilder.ApplyConfiguration(new UsuarioAreaAtendimentoMap());
            modelBuilder.ApplyConfiguration(new UsuarioMap());

            modelBuilder.HasSequence("historico_senha_cd_senha_seq");
        }
    }
}
